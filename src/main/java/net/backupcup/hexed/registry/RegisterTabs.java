package net.backupcup.hexed.registry;

import net.backupcup.hexed.Hexed;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegisterTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Hexed.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> HEXED_GROUP = CREATIVE_MODE_TABS.register("hexed", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.hexed"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> RegisterItems.BOOK.asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                RegisterItems.ITEMS.getEntries().forEach((i) -> {
                            output.accept(i.get().asItem());
                        }
                );
            }).build());

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {}
}
