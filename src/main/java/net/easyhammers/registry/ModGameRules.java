package net.easyhammers.registry;

import net.minecraft.world.level.GameRules;

public class ModGameRules {
    public static GameRules.Key<GameRules.BooleanValue> DAMAGE_HAMMER_BY_BLOCK_COUNT;
    public static GameRules.Key<GameRules.BooleanValue> EASY_HAMMERS_SNEAKING_MODE;

    public static void register() {
        DAMAGE_HAMMER_BY_BLOCK_COUNT = GameRules.register("damageHammerByBlockCount", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
        EASY_HAMMERS_SNEAKING_MODE = GameRules.register("easyhammersSneakingMode", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
    }
}
