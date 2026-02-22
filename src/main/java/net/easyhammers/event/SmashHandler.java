package net.easyhammers.event;

import net.easyhammers.registry.ModEnchantments;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class SmashHandler {

    public static void register() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((ServerLevel world, Entity entity, LivingEntity killedEntity) -> {
           // We moved the actual active combat logic into the Mixin on normal attacks to better replicate CriticalHits.
           // This class can be preserved for extending future death-related events if needed.
        });
    }
}
