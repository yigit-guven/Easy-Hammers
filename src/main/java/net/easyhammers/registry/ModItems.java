package net.easyhammers.registry;

import net.easyhammers.EasyHammersMod;
import net.easyhammers.content.HammerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EasyHammersMod.MODID);

    public static final RegistryObject<Item> WOODEN_HAMMER = registerHammer("wooden_hammer", ModToolTiers.WOODEN, 5.5F, -3.2F);
    public static final RegistryObject<Item> STONE_HAMMER = registerHammer("stone_hammer", ModToolTiers.STONE, 5.5F, -3.2F);
    public static final RegistryObject<Item> IRON_HAMMER = registerHammer("iron_hammer", ModToolTiers.IRON, 6.0F, -3.1F);
    public static final RegistryObject<Item> GOLDEN_HAMMER = registerHammer("golden_hammer", ModToolTiers.GOLD, 5.5F, -3.0F);
    public static final RegistryObject<Item> DIAMOND_HAMMER = registerHammer("diamond_hammer", ModToolTiers.DIAMOND, 6.5F, -3.1F);
    public static final RegistryObject<Item> NETHERITE_HAMMER = registerHammer("netherite_hammer", ModToolTiers.NETHERITE, 7.0F, -3.0F);

    private static RegistryObject<Item> registerHammer(String name, Tier tier, float attackDamage, float attackSpeed) {
        return ITEMS.register(name, () -> new HammerItem(tier, attackDamage, attackSpeed, new Item.Properties().tab(ModTabs.EASY_HAMMERS_TAB)));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
