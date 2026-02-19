package net.easyhammers.event;

import net.easyhammers.EasyHammersMod;
import net.easyhammers.registry.ModEnchantments;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EasyHammersMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SmashHandler {

    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        if (event.getEntity() == null || event.getTarget() == null) return;
        
        ItemStack mainHand = event.getEntity().getMainHandItem();
        int level = mainHand.getEnchantmentLevel(ModEnchantments.SMASH.get());
        
        if (level > 0) {
            double stunChance = 0.3 + 0.2 * level;
            
            if (event.getEntity().getRandom().nextDouble() < stunChance) {
               event.getTarget().getPersistentData().putDouble("stunduration", 100);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTick(TickEvent.PlayerTickEvent event) {
        // PlayerTickEvent is for players. We want any LivingEntity that is stunned.
        // Actually, we stun the TARGET, which might be a mob.
        // So we need LivingTickEvent or similar.
    }
    
    @SubscribeEvent
    public static void onLivingTick(net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
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
