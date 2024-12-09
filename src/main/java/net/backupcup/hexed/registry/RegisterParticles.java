package net.backupcup.hexed.registry;

import net.backupcup.hexed.Hexed;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleTypes;
import team.lodestar.lodestone.systems.particle.screen.LodestoneScreenParticleType;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleOptions;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleType;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Hexed.MOD_ID);

    public static final Map<String, DeferredHolder<ParticleType<?>, LodestoneWorldParticleType>> particleMapWorld = new HashMap<>();
    public static final Map<String, ScreenParticleType<ScreenParticleOptions>> particleMapScreen = new HashMap<>();

    public static final List<String> RUNE_LIST = List.of(
            "aether", "air", "alcohol", "amber", "black_tourmaline", "blood",
            "bone", "copper", "cosmic_dust", "earth", "fire",
            "glass", "gold", "ichor", "iron", "lead", "limestone",
            "mercury", "moon", "nether", "opium", "philosopher_stone", "salt",
            "shroom", "silver", "soul", "sulfur", "time", "tin",
            "water", "wood"
    );

    public static void initializeParticles() {
        RUNE_LIST.forEach(RegisterParticles::registerParticle);
    }

    private static void registerParticle(String name) {
        var worldParticle = PARTICLE_TYPES.register(name, LodestoneWorldParticleType::new);
        particleMapWorld.put(name, worldParticle);

        var screenParticle = LodestoneScreenParticleTypes.registerType(new LodestoneScreenParticleType());
        particleMapScreen.put(name, screenParticle);
    }

    public static void registerParticleFactory(RegisterParticleProvidersEvent event) {
        particleMapWorld.forEach((name, particle) ->
                Minecraft.getInstance().particleEngine.register(particle.get(), LodestoneWorldParticleType.Factory::new)
        );
    }

    public static void registerScreenParticleFactory(RegisterParticleProvidersEvent event) {
        particleMapScreen.forEach((name, screenParticle) ->
                LodestoneScreenParticleTypes.registerProvider(
                        screenParticle,
                        new LodestoneScreenParticleType.Factory(LodestoneScreenParticleTypes.getSpriteSet(Hexed.getPath(name)))
                )
        );
    }

}