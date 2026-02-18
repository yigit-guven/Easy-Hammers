package net.easyhammers.registry;

import net.easyhammers.EasyHammersMod;
import net.easyhammers.content.HammerItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
// import net.minecraft.world.item.ToolMaterial; // Removed in 1.21.1
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, EasyHammersMod.MODID);

    public static final Supplier<Item> WOODEN_HAMMER = registerHammer("wooden_hammer", ModToolTiers.WOODEN, 5.5F, -3.2F);
    public static final Supplier<Item> STONE_HAMMER = registerHammer("stone_hammer", ModToolTiers.STONE, 5.5F, -3.2F);
    public static final Supplier<Item> IRON_HAMMER = registerHammer("iron_hammer", ModToolTiers.IRON, 6.0F, -3.1F);
    public static final Supplier<Item> GOLDEN_HAMMER = registerHammer("golden_hammer", ModToolTiers.GOLD, 5.5F, -3.0F);
    public static final Supplier<Item> DIAMOND_HAMMER = registerHammer("diamond_hammer", ModToolTiers.DIAMOND, 6.5F, -3.1F);
    public static final Supplier<Item> NETHERITE_HAMMER = registerHammer("netherite_hammer", ModToolTiers.NETHERITE, 7.0F, -3.0F);

    private static Supplier<Item> registerHammer(String name, net.minecraft.world.item.Tier tier, float attackDamage, float attackSpeed) {
        return ITEMS.register(name, () -> new HammerItem(tier, attackDamage, attackSpeed, new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
