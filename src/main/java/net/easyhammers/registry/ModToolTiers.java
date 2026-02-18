package net.easyhammers.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Tier;

public class ModToolTiers {
    public static final Tier WOODEN = new HammerTier(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 236, 2.0F, 0.0F, 15, net.minecraft.world.item.crafting.Ingredient.of(ItemTags.PLANKS));
    public static final Tier STONE = new HammerTier(BlockTags.INCORRECT_FOR_STONE_TOOL, 524, 4.0F, 1.0F, 5, net.minecraft.world.item.crafting.Ingredient.of(ItemTags.STONE_TOOL_MATERIALS));
    public static final Tier IRON = new HammerTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 1000, 6.0F, 2.0F, 14, net.minecraft.world.item.crafting.Ingredient.of(net.minecraft.world.item.Items.IRON_INGOT));
    public static final Tier GOLD = new HammerTier(BlockTags.INCORRECT_FOR_GOLD_TOOL, 128, 12.0F, 0.0F, 22, net.minecraft.world.item.crafting.Ingredient.of(net.minecraft.world.item.Items.GOLD_INGOT));
    public static final Tier DIAMOND = new HammerTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 6244, 8.0F, 3.0F, 10, net.minecraft.world.item.crafting.Ingredient.of(net.minecraft.world.item.Items.DIAMOND));
    public static final Tier NETHERITE = new HammerTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8124, 9.0F, 4.0F, 15, net.minecraft.world.item.crafting.Ingredient.of(net.minecraft.world.item.Items.NETHERITE_INGOT));

    public record HammerTier(net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> incorrectBlocksForDrops, int uses, float speed, float attackDamageBonus, int enchantmentValue, net.minecraft.world.item.crafting.Ingredient repairIngredient) implements Tier {
        @Override
        public int getUses() { return uses; }
        @Override
        public float getSpeed() { return speed; }
        @Override
        public float getAttackDamageBonus() { return attackDamageBonus; }
        @Override
        public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
        @Override
        public int getEnchantmentValue() { return enchantmentValue; }
        @Override
        public net.minecraft.world.item.crafting.Ingredient getRepairIngredient() { return repairIngredient; }
    }
}
