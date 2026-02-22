package net.easyhammers.mixin;

import net.easyhammers.registry.ModEnchantments;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", shift = At.Shift.AFTER))
    private void onCriticalHit(Entity target, CallbackInfo ci) {
        if (!(target instanceof LivingEntity livingTarget)) return;

        Player player = (Player) (Object) this;
        ItemStack mainHand = player.getMainHandItem();

        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SMASH, mainHand);

        if (level > 0) {
            boolean isCritical = player.fallDistance > 0.0F && !player.onGround() && !player.onClimbable() && !player.isInWater() && !player.hasEffect(MobEffects.BLINDNESS) && !player.isPassenger();
            
            if (isCritical) {
                double stunChance = 0.3 + 0.2 * level;
                if (player.getRandom().nextDouble() < stunChance) {
                    livingTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 9, false, false, true));
                    livingTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 9, false, false, true));
                }
            }
        }
    }
}
