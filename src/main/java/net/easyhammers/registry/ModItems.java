package net.easyhammers.registry;

import net.easyhammers.EasyHammers;
import net.easyhammers.content.HammerItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {

    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", 
        new HammerItem(ModToolMaterials.WOODEN, 5.5F, -3.2F, new FabricItemSettings()));
    public static final Item STONE_HAMMER = registerItem("stone_hammer", 
        new HammerItem(ModToolMaterials.STONE, 5.5F, -3.2F, new FabricItemSettings()));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", 
        new HammerItem(ModToolMaterials.IRON, 6.0F, -3.1F, new FabricItemSettings()));
    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", 
        new HammerItem(ModToolMaterials.GOLD, 5.5F, -3.0F, new FabricItemSettings()));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", 
        new HammerItem(ModToolMaterials.DIAMOND, 6.5F, -3.1F, new FabricItemSettings()));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", 
        new HammerItem(ModToolMaterials.NETHERITE, 7.0F, -3.0F, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EasyHammers.MODID, name), item);
    }

    public static void registerItems() {
        EasyHammers.LOGGER.info("Registering Mod Items for {}", EasyHammers.MODID);
    }
}
