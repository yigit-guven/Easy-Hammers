package net.easyhammers;

import net.easyhammers.registry.ModItems;
import net.easyhammers.registry.ModTabs;
import net.easyhammers.registry.ModGameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(EasyHammersMod.MODID)
public class EasyHammersMod {
    public static final String MODID = "easy_hammers";

    public EasyHammersMod(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        ModTabs.register(modEventBus);
        ModGameRules.register();
    }
}
