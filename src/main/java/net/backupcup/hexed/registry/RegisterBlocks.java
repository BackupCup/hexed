package net.backupcup.hexed.registry;

import com.google.common.collect.Sets;
import net.backupcup.hexed.Hexed;
import net.backupcup.hexed.block.MaledictionEssenceBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class RegisterBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Hexed.MOD_ID);

    public static LinkedHashSet<DeferredHolder<Item, BlockItem>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();


    public static final DeferredBlock<MaledictionEssenceBlock> MALEDICTION_ESSENCE = registerBlockWithoutItem("malediction_essence",
            () -> new MaledictionEssenceBlock((BlockBehaviour.Properties.of())
                    .mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.HONEY_BLOCK)
                    .randomTicks()
            ));


    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(String name, Supplier<? extends Block> block) {
        DeferredBlock<Block> toReturn = BLOCKS.register(name, block);
        return (DeferredBlock<T>) toReturn;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<? extends Block> block) {
        DeferredBlock<Block> toReturn = BLOCKS.register(name, block);
        CREATIVE_TAB_ITEMS.add(registerBlockItem(name, toReturn));
        return (DeferredBlock<T>) toReturn;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Block> DeferredBlock<T> registerIntegrationBlockWithoutItem(String name, Supplier<? extends Block> block, String modId) {
        if (!ModList.get().isLoaded(modId)) return null;
        DeferredBlock<Block> toReturn = BLOCKS.register(name, block);
        return (DeferredBlock<T>) toReturn;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Block> DeferredBlock<T> registerIntegrationBlock(String name, Supplier<? extends Block> block, String modId) {
        if (!ModList.get().isLoaded(modId)) return null;
        DeferredBlock<Block> toReturn = BLOCKS.register(name, block);
        CREATIVE_TAB_ITEMS.add(registerIntegrationBlockItem(name, toReturn));
        return (DeferredBlock<T>) toReturn;
    }

    private static DeferredHolder<Item, BlockItem> registerBlockItem(String name, Supplier<? extends Block> block) {
        return RegisterItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static DeferredHolder<Item, BlockItem> registerIntegrationBlockItem(String name, Supplier<? extends Block> block) {
        return RegisterItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
