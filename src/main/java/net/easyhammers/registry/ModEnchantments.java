package net.easyhammers.registry;

import net.easyhammers.EasyHammers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantments {

    public static final Enchantment SMASH = registerEnchantment("smash", 
        new SmashEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static final Enchantment SOIL_BREAKER = registerEnchantment("soil_breaker", 
        new SoilBreakerEnchantment(Enchantment.Rarity.COMMON, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    private static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT, new ResourceLocation(EasyHammers.MODID, name), enchantment);
    }

    public static void registerEnchantments() {
        EasyHammers.LOGGER.info("Registering Mod Enchantments for {}", EasyHammers.MODID);
    }

    public static class SmashEnchantment extends Enchantment {
        protected SmashEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slotTypes) {
            super(rarity, category, slotTypes);
        }

        @Override
        public int getMaxLevel() { return 5; }
    }

    public static class SoilBreakerEnchantment extends Enchantment {
        protected SoilBreakerEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slotTypes) {
            super(rarity, category, slotTypes);
        }

        @Override
        public int getMaxLevel() { return 1; }
    }
}
