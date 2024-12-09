package net.backupcup.hexed;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import static net.backupcup.hexed.Hexed.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HexedClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }
}
