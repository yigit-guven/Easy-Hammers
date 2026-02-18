package net.easyhammers.content;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.easyhammers.registry.ModGameRules;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends Item {
    public HammerItem(ToolMaterial tier, float attackDamage, float attackSpeed, Properties properties) {
        super(properties.pickaxe(tier, attackDamage, attackSpeed));
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide() && state.getDestroySpeed(level, pos) != 0.0F) {
            stack.hurtAndBreak(1, entity, net.minecraft.world.entity.EquipmentSlot.MAINHAND);

            if (entity instanceof ServerPlayer player) {
                // Check Sneak Mode GameRule
                // Start by casting level to ServerLevel since we know !isClientSide
                ServerLevel serverLevel = (ServerLevel) level;
                boolean sneakMode = serverLevel.getGameRules().get(net.easyhammers.registry.ModGameRules.EASY_HAMMERS_SNEAKING_MODE.get());
                if (sneakMode && player.isShiftKeyDown()) {
                    return true; // Sneaking with rule enabled -> act like normal pickaxe
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
    
    // ... getPlayerPOVHitResult stays same ...

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
        double d0 = player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE);
        var vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, fluidMode, player));
    }

    private void breakNeighbors(Level level, BlockPos pos, ServerPlayer player, ItemStack stack, Direction face) {
        List<BlockPos> neighbors = getNeighbors(pos, face);
        
        // Check for Soil Breaker
        int soilBreakerLevel = stack.getEnchantmentLevel(level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(net.easyhammers.registry.ModEnchantmentKeys.SOIL_BREAKER));
        boolean hasSoilBreaker = soilBreakerLevel > 0;

        // Check Damage Rule
        // Cast to ServerLevel safely here too as we are called from server side context
        boolean damageByCount = ((net.minecraft.server.level.ServerLevel)level).getGameRules().get(net.easyhammers.registry.ModGameRules.DAMAGE_HAMMER_BY_BLOCK_COUNT.get());

        for (BlockPos neighborPos : neighbors) {
            BlockState neighborState = level.getBlockState(neighborPos);
            boolean isSoil = neighborState.is(BlockTags.MINEABLE_WITH_SHOVEL);
            
            if (isCorrectToolForDrops(stack, neighborState) || (hasSoilBreaker && isSoil)) {
                boolean destroyed = player.gameMode.destroyBlock(neighborPos);
                if (destroyed && damageByCount) {
                    stack.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);
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
