package net.easyhammers;

import net.easyhammers.registry.ModItems;
import net.easyhammers.registry.ModTabs;
import net.easyhammers.registry.ModEnchantments;
import net.easyhammers.registry.ModGameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EasyHammersMod.MODID)
public class EasyHammersMod {
    public static final String MODID = "easy_hammers";

    public EasyHammersMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModTabs.register(modEventBus);
        ModEnchantments.register(modEventBus);
        
        // GameRules in 1.20.1 are simpler usually, let's keep the static register call for now
        ModGameRules.register(); 

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
