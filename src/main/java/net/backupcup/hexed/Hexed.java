package net.backupcup.hexed;

import net.backupcup.hexed.registry.RegisterItems;
import net.backupcup.hexed.registry.RegisterParticles;
import net.backupcup.hexed.registry.RegisterTabs;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Hexed.MOD_ID)
public class Hexed {
    public static final String MOD_ID = "hexed";
    public static ResourceLocation getPath(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public Hexed(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        RegisterItems.ITEMS.register(modEventBus);
        RegisterTabs.CREATIVE_MODE_TABS.register(modEventBus);
        RegisterParticles.PARTICLE_TYPES.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}