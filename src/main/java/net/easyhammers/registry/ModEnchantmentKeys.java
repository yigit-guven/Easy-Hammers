package net.easyhammers.registry;

import net.easyhammers.EasyHammersMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantmentKeys {
    public static final ResourceKey<Enchantment> SOIL_BREAKER = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EasyHammersMod.MODID, "soil_breaker"));
    public static final ResourceKey<Enchantment> SMASH = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EasyHammersMod.MODID, "smash"));
}
