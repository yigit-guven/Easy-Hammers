package net.easyhammers.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {
    public static final CreativeModeTab EASY_HAMMERS_TAB = new CreativeModeTab("easy_hammers_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.DIAMOND_HAMMER.get());
        }
    };
}

