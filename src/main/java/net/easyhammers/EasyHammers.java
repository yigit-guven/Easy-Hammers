package net.easyhammers;

import net.easyhammers.registry.ModEnchantments;
import net.easyhammers.registry.ModGameRules;
import net.easyhammers.registry.ModItems;
import net.easyhammers.registry.ModTabs;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyHammers implements ModInitializer {
    public static final String MODID = "easy_hammers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModTabs.registerTabs();
        ModEnchantments.registerEnchantments();
        ModGameRules.registerGameRules();

        LOGGER.info("Easy Hammers initialized.");
    }
}
