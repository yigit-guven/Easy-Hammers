package net.easyhammers.registry;

import net.easyhammers.EasyHammersMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;
import net.minecraft.world.flag.FeatureFlagSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;

public class ModGameRules {
    public static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(Registries.GAME_RULE, EasyHammersMod.MODID);

    public static final DeferredHolder<GameRule<?>, GameRule<Boolean>> DAMAGE_HAMMER_BY_BLOCK_COUNT = GAME_RULES.register("damage_hammer_by_block_count", 
        () -> new GameRule<Boolean>(GameRuleCategory.PLAYER, GameRuleType.BOOL, BoolArgumentType.bool(), (visitor, rule) -> visitor.visitBoolean(rule), Codec.BOOL, v -> v ? 1 : 0, true, FeatureFlagSet.of()));

    public static final DeferredHolder<GameRule<?>, GameRule<Boolean>> EASY_HAMMERS_SNEAKING_MODE = GAME_RULES.register("easy_hammers_sneaking_mode", 
        () -> new GameRule<Boolean>(GameRuleCategory.PLAYER, GameRuleType.BOOL, BoolArgumentType.bool(), (visitor, rule) -> visitor.visitBoolean(rule), Codec.BOOL, v -> v ? 1 : 0, true, FeatureFlagSet.of()));

    public static void register(IEventBus modEventBus) {
        GAME_RULES.register(modEventBus);
    }
}
