package net.easyhammers.registry;

import net.easyhammers.EasyHammersMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.GameRules;

public class ModGameRules {
    public static final GameRules.Key<GameRules.BooleanValue> DAMAGE_HAMMER_BY_BLOCK_COUNT =
            GameRules.register("damage_hammer_by_block_count", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
    
    public static final GameRules.Key<GameRules.BooleanValue> EASY_HAMMERS_SNEAKING_MODE =
            GameRules.register("easy_hammers_sneaking_mode", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static void register() {
         // Static initialization handles registration. This method can be empty or used to force class loading.
    }
}
