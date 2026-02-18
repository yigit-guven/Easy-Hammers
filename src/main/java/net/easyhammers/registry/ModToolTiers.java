package net.easyhammers.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolTiers {
    public static final ToolMaterial WOODEN = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 236, 2.0F, 0.0F, 15, ItemTags.PLANKS);
    public static final ToolMaterial STONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 524, 4.0F, 1.0F, 5, ItemTags.STONE_TOOL_MATERIALS);
    public static final ToolMaterial IRON = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1000, 6.0F, 2.0F, 14, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial GOLD = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 128, 12.0F, 0.0F, 22, ItemTags.GOLD_TOOL_MATERIALS);
    public static final ToolMaterial DIAMOND = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 6244, 8.0F, 3.0F, 10, ItemTags.DIAMOND_TOOL_MATERIALS);
    public static final ToolMaterial NETHERITE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8124, 9.0F, 4.0F, 15, ItemTags.NETHERITE_TOOL_MATERIALS);
}
