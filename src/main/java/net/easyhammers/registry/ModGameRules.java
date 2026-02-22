package net.easyhammers.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class ModGameRules {

    public static GameRules.Key<GameRules.BooleanValue> DAMAGE_HAMMER_BY_BLOCK_COUNT;
    public static GameRules.Key<GameRules.BooleanValue> EASY_HAMMERS_SNEAKING_MODE;

    public static void registerGameRules() {
        DAMAGE_HAMMER_BY_BLOCK_COUNT = GameRuleRegistry.register(
            "damageHammerByBlockCount", 
            GameRules.Category.PLAYER, 
            GameRuleFactory.createBooleanRule(true)
        );

        EASY_HAMMERS_SNEAKING_MODE = GameRuleRegistry.register(
            "easyhammersSneakingMode", 
            GameRules.Category.PLAYER, 
            GameRuleFactory.createBooleanRule(true)
        );
    }
}
