package net.easyhammers.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModToolTiers {
    public static final Tier WOODEN = new ForgeTier(0, 59, 2.0F, 0.0F, 15, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemTags.PLANKS));
    public static final Tier STONE = new ForgeTier(1, 131, 4.0F, 1.0F, 5, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS));
    public static final Tier IRON = new ForgeTier(2, 250, 6.0F, 2.0F, 14, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(net.minecraft.world.item.Items.IRON_INGOT));
    public static final Tier GOLD = new ForgeTier(0, 32, 12.0F, 0.0F, 22, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(net.minecraft.world.item.Items.GOLD_INGOT));
    public static final Tier DIAMOND = new ForgeTier(3, 1561, 8.0F, 3.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(net.minecraft.world.item.Items.DIAMOND));
    public static final Tier NETHERITE = new ForgeTier(4, 2031, 9.0F, 4.0F, 15, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(net.minecraft.world.item.Items.NETHERITE_INGOT));
}
