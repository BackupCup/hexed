package net.backupcup.hexed.registry;

import com.google.common.collect.Sets;
import net.backupcup.hexed.Hexed;
import net.backupcup.hexed.item.CompendiumItem;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class RegisterItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Hexed.MOD_ID);
    public static LinkedHashSet<DeferredItem<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static final DeferredItem<Item> BOOK = registerItem("hexbinder_book", () -> new CompendiumItem(new Item.Properties()));

    public static <T extends Item> DeferredItem<T> registerItem(final String name, final Supplier<? extends Item> item) {
        DeferredItem<Item> toReturn = ITEMS.register(name, item);
        CREATIVE_TAB_ITEMS.add(toReturn);
        return (DeferredItem<T>) toReturn;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Item> DeferredItem<T> registerIntegrationItem(final String name, final Supplier<? extends Item> item, String modId) {
        if (!ModList.get().isLoaded(modId)) return null;
        DeferredItem<Item> toReturn = ITEMS.register(name, item);
        CREATIVE_TAB_ITEMS.add(toReturn);
        return (DeferredItem<T>) toReturn;
    }
}
