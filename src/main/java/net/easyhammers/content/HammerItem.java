package net.easyhammers.content;

import net.easyhammers.registry.ModEnchantments;
import net.easyhammers.registry.ModGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends DiggerItem {

    public HammerItem(Tier material, float attackDamage, float attackSpeed, Properties settings) {
        super(attackDamage, attackSpeed, material, BlockTags.MINEABLE_WITH_PICKAXE, settings);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F) {
            stack.hurtAndBreak(1, miner, (e) -> e.broadcastBreakEvent(miner.getUsedItemHand() == net.minecraft.world.InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));

            if (miner instanceof ServerPlayer player) {
                boolean sneakMode = world.getGameRules().getBoolean(ModGameRules.EASY_HAMMERS_SNEAKING_MODE);
                if (sneakMode && player.isShiftKeyDown()) {
                    return true;
                }

                BlockHitResult ray = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
                if (ray.getType() == HitResult.Type.BLOCK && ray.getBlockPos().equals(pos)) {
                    Direction face = ray.getDirection();
                    breakNeighbors(world, pos, player, stack, face);
                }
            }
        }
        return true;
    }

    public static BlockHitResult getPlayerPOVHitResult(Level world, Player player, ClipContext.Fluid fluidHandling) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        var vec3 = player.getEyePosition();
        float f2 = net.minecraft.util.Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = net.minecraft.util.Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -net.minecraft.util.Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = net.minecraft.util.Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        
        // 4.5 blocks vanilla default for reach
        double d0 = 4.5d;

        var vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return world.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, fluidHandling, player));
    }

    private void breakNeighbors(Level world, BlockPos pos, ServerPlayer player, ItemStack stack, Direction face) {
        List<BlockPos> neighbors = getNeighbors(pos, face);

        int soilBreakerLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SOIL_BREAKER, stack);
        boolean hasSoilBreaker = soilBreakerLevel > 0;

        boolean damageByCount = world.getGameRules().getBoolean(ModGameRules.DAMAGE_HAMMER_BY_BLOCK_COUNT);

        for (BlockPos neighborPos : neighbors) {
            BlockState neighborState = world.getBlockState(neighborPos);
            boolean isSoil = neighborState.is(BlockTags.MINEABLE_WITH_SHOVEL);

            if (isCorrectToolForDrops(neighborState) || (hasSoilBreaker && isSoil)) {
                boolean destroyed = player.gameMode.destroyBlock(neighborPos);
                if (destroyed && damageByCount) {
                    stack.hurtAndBreak(1, player, (e) -> e.broadcastBreakEvent(player.getUsedItemHand() == net.minecraft.world.InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));
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
