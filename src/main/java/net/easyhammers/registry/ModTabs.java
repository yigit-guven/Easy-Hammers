package net.easyhammers.registry;

import net.easyhammers.EasyHammersMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EasyHammersMod.MODID);

    public static final Supplier<CreativeModeTab> EASY_HAMMERS_TAB = CREATIVE_MODE_TABS.register("easy_hammers_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.easy_hammers.easy_hammers_tab"))
            .icon(() -> new ItemStack(ModItems.DIAMOND_HAMMER.get()))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.WOODEN_HAMMER.get());
                output.accept(ModItems.STONE_HAMMER.get());
                output.accept(ModItems.IRON_HAMMER.get());
                output.accept(ModItems.GOLDEN_HAMMER.get());
                output.accept(ModItems.DIAMOND_HAMMER.get());
                output.accept(ModItems.NETHERITE_HAMMER.get());
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
