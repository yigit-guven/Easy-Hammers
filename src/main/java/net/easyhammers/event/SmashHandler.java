package net.easyhammers.event;

import net.easyhammers.EasyHammersMod;
import net.easyhammers.registry.ModEnchantmentKeys;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

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
            // The original code calculated damage modifier but didn't use it.
            // It only applied "stunduration" to persistent data.
            // We'll replicate that.
            
            if (event.getEntity().getRandom().nextDouble() < stunChance) {
               event.getTarget().getPersistentData().putDouble("stunduration", 100);
            }
        }
    }
}
