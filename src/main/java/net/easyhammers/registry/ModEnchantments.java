package net.easyhammers.registry;

import net.easyhammers.EasyHammersMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EasyHammersMod.MODID);

    // In 1.20.1, we define Enchantment classes or anonymous classes
    public static final RegistryObject<Enchantment> SMASH = ENCHANTMENTS.register("smash", 
        () -> new SmashEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> SOIL_BREAKER = ENCHANTMENTS.register("soil_breaker",
        () -> new SoilBreakerEnchantment(Enchantment.Rarity.COMMON, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
    
    // Inner classes for simple enchantments if they don't exist elsewhere
    public static class SmashEnchantment extends Enchantment {
        protected SmashEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
            super(rarity, category, slots);
        }
        
        @Override
        public int getMaxLevel() { return 5; }
    }

    public static class SoilBreakerEnchantment extends Enchantment {
        protected SoilBreakerEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
            super(rarity, category, slots);
        }
        
        @Override
        public int getMaxLevel() { return 1; }
    }
}
