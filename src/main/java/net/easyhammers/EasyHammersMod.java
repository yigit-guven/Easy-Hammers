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

        // GameRules are registered during class loading/initialization of GameRules class normally, 
        // but we need to ensure our class is loaded.
        // NeoForge might have a dedicated event or mechanism, but vanilla GameRules.register is static.
        // We can call it here or in FMLCommonSetupEvent. 
        // Actually, vanilla registers them in static block of GameRules.
        // To be safe and ensure they exist, we call the static register method.
        ModGameRules.register();
    }
}
