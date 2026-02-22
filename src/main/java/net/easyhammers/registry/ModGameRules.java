package net.easyhammers.registry;

import net.minecraft.world.level.GameRules;

public class ModGameRules {
    public static boolean DAMAGE_HAMMER_BY_BLOCK_COUNT = true;
    public static boolean EASY_HAMMERS_SNEAKING_MODE = true;

    public static void register() {
        // GameRules registration requires AT in 1.19.2, using static fields as workaround for downgrade config.
    }
}
