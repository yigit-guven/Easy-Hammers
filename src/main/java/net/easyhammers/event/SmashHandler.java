package net.easyhammers.event;

import net.easyhammers.EasyHammersMod;
import net.easyhammers.registry.ModEnchantmentKeys;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

@EventBusSubscriber(modid = EasyHammersMod.MODID)
public class SmashHandler {

    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        if (event.getEntity() == null || event.getTarget() == null) return;
        
        ItemStack mainHand = event.getEntity().getMainHandItem();
        var registryAccess = event.getEntity().registryAccess();
        
        // Check for Smash enchantment
        // In 1.21, we use EnchantmentHelper or getEnchantmentLevel with registry lookup
        int level = mainHand.getEnchantmentLevel(registryAccess.lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getOrThrow(ModEnchantmentKeys.SMASH));
        
        if (level > 0) {
            double stunChance = 0.3 + 0.2 * level;
            
            if (event.getEntity().getRandom().nextDouble() < stunChance) {
               event.getTarget().getPersistentData().putDouble("stunduration", 100);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            if (entity.getPersistentData().contains("stunduration")) {
                double duration = entity.getPersistentData().getDouble("stunduration");
                
                if (duration > 0) {
                    // Apply Slowness X (high enough to stop movement)
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 10, false, false, true));
                    // Apply Weakness X (reduce damage significantly)
                    entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 2, 10, false, false, true));
                    
                    entity.getPersistentData().putDouble("stunduration", duration - 1);
                } else {
                    entity.getPersistentData().remove("stunduration");
                }
            }
        }
    }
}
