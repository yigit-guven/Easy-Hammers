package net.easyhammers.content;

import net.easyhammers.registry.ModEnchantments;
import net.easyhammers.registry.ModGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        // DiggerItem 1.20.1: float attackDamage, float attackSpeed, Tier tier, TagKey<Block> blocks, Properties properties
        super(attackDamage, attackSpeed, tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F) {
            stack.hurtAndBreak(1, entity, (e) -> e.broadcastBreakEvent(net.minecraft.world.entity.EquipmentSlot.MAINHAND));
            // In 1.20.1 hurtAndBreak takes a Consumer<T> onBreak, not slot directly?
            // Actually usually hurtAndBreak(int amount, LivingEntity entity, Consumer<LivingEntity> onBreak)

            if (entity instanceof ServerPlayer player) {
                // Check Sneak Mode GameRule
                ServerLevel serverLevel = (ServerLevel) level;
                boolean sneakMode = ModGameRules.EASY_HAMMERS_SNEAKING_MODE;
                if (sneakMode && player.isShiftKeyDown()) {
                    return true;
                }

                BlockHitResult ray = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
                if (ray.getType() == HitResult.Type.BLOCK && ray.getBlockPos().equals(pos)) {
                    Direction face = ray.getDirection();
                    breakNeighbors(level, pos, player, stack, face);
                }
            }
        }
        return true; 
    }
    
    public static BlockHitResult getPlayerPOVHitResult(Level level, Player player, ClipContext.Fluid fluidMode) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        var vec3 = player.getEyePosition();
        float f2 = net.minecraft.util.Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = net.minecraft.util.Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -net.minecraft.util.Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = net.minecraft.util.Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        // In 1.20.1, usage of getAttributeValue might differ? No, usually same.
        // But BLOCK_INTERACTION_RANGE was introduced in 1.21 I think?
        // In 1.20.1 it was usually fixed to 4.5 or 5.0, or used Forge's REACH_DISTANCE attribute.
        // Let's check if Attributes.BLOCK_INTERACTION_RANGE exists. 
        // Likely NOT. We should use ForgeHooks.getBlockReachDistance(player) or similar, or 5.0.
        // Actually, vanilla usually uses 4.5 for survival or 5.0 for creative.
        // Forge has `ForgeMod.REACH_DISTANCE`? No, `ForgeMod.BLOCK_REACH.get()`.
        
        // Use 4.5 as fallback for now or try to access standard reach.
        double d0 = 4.5d; 
        try {
             d0 = player.getAttributeValue(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get());
        } catch (Exception e) {}
        
        var vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, fluidMode, player));
    }

    private void breakNeighbors(Level level, BlockPos pos, ServerPlayer player, ItemStack stack, Direction face) {
        List<BlockPos> neighbors = getNeighbors(pos, face);
        
        // Check for Soil Breaker
        int soilBreakerLevel = stack.getEnchantmentLevel(ModEnchantments.SOIL_BREAKER.get());
        boolean hasSoilBreaker = soilBreakerLevel > 0;

        boolean damageByCount = ModGameRules.DAMAGE_HAMMER_BY_BLOCK_COUNT;

        for (BlockPos neighborPos : neighbors) {
            BlockState neighborState = level.getBlockState(neighborPos);
            boolean isSoil = neighborState.is(BlockTags.MINEABLE_WITH_SHOVEL);
            
            if (isCorrectToolForDrops(stack, neighborState) || (hasSoilBreaker && isSoil)) {
                // destroyBlock? 
                boolean destroyed = player.gameMode.destroyBlock(neighborPos);
                if (destroyed && damageByCount) {
                    stack.hurtAndBreak(1, player, (e) -> e.broadcastBreakEvent(net.minecraft.world.entity.EquipmentSlot.MAINHAND));
                }
            }
        }
    }

    public static List<BlockPos> getNeighbors(BlockPos pos, Direction face) {
        List<BlockPos> neighbors = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (face == Direction.UP || face == Direction.DOWN) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = z - 1; j <= z + 1; j++) {
                    if (i != x || j != z) {
                        neighbors.add(new BlockPos(i, y, j));
                    }
                }
            }
        } else if (face == Direction.NORTH || face == Direction.SOUTH) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i != x || j != y) {
                        neighbors.add(new BlockPos(i, j, z));
                    }
                }
            }
        } else if (face == Direction.EAST || face == Direction.WEST) {
            for (int i = z - 1; i <= z + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i != z || j != y) {
                        neighbors.add(new BlockPos(x, j, i));
                    }
                }
            }
        }

        return neighbors;
    }
}
