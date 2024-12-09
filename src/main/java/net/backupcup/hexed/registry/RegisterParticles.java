package net.backupcup.hexed.registry;

import net.backupcup.hexed.Hexed;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleTypes;
import team.lodestar.lodestone.systems.particle.screen.LodestoneScreenParticleType;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleOptions;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleType;
import team.lodestar.lodestone.systems.particle.screen.base.ScreenParticle;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

public class RegisterParticles {
    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Hexed.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, ParticleType<?>> FIRE_RUNE_PARTICLE =
            PARTICLE_TYPES.register("fire", () -> new LodestoneWorldParticleType(){});

    public static final ScreenParticleType<ScreenParticleOptions> FIRE_RUNE_SCREEN =
            LodestoneScreenParticleTypes.registerType(new LodestoneScreenParticleType());

    public static void registerParticleFactory(RegisterParticleProvidersEvent event) {
        LodestoneScreenParticleTypes.registerProvider(FIRE_RUNE_SCREEN,
                new LodestoneScreenParticleType.Factory(LodestoneScreenParticleTypes.getSpriteSet(Hexed.getPath("fire"))));
    }
}
