package net.easyhammers.registry;

import net.easyhammers.EasyHammers;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {
    public static final ResourceKey<CreativeModeTab> EASY_HAMMERS_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(EasyHammers.MODID, "easy_hammers_tab"));

    public static final CreativeModeTab EASY_HAMMERS_TAB = FabricItemGroup.builder()
            .title(Component.translatable("item_group.easy_hammers.easy_hammers_tab"))
            .icon(() -> new ItemStack(ModItems.DIAMOND_HAMMER))
            .displayItems((displayContext, entries) -> {
                entries.accept(ModItems.WOODEN_HAMMER);
                entries.accept(ModItems.STONE_HAMMER);
                entries.accept(ModItems.IRON_HAMMER);
                entries.accept(ModItems.GOLDEN_HAMMER);
                entries.accept(ModItems.DIAMOND_HAMMER);
                entries.accept(ModItems.NETHERITE_HAMMER);
            })
            .build();

    public static void registerTabs() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, EASY_HAMMERS_TAB_KEY, EASY_HAMMERS_TAB);
    }
}
