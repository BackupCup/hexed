package net.backupcup.hexed.item;

import net.backupcup.hexed.registry.RegisterParticles;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import oshi.util.tuples.Pair;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler;
import team.lodestar.lodestone.handlers.screenparticle.ScreenParticleHandler;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleTypes;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleDataBuilder;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleHolder;

import java.awt.*;

public class CompendiumItem extends Item implements ParticleEmitterHandler.ItemParticleSupplier {
    public CompendiumItem(Properties properties) {
        super(properties);
    }

    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        float gameTime = level.getGameTime();
        Pair<Color, Color> colorPair = new Pair<>(new Color(0x8A1B29), new Color(0xDC001E));

        SpinParticleDataBuilder spinDataBuilder = SpinParticleData.create(0f, 1f).setSpinOffset(0.025f * gameTime % 6.28f).setEasing(Easing.EXPO_IN_OUT);

        for (int i = 0; i < 2; i++) {
            int xOffset = (int) (level.random.nextFloat() * 32 - 16);
            int yOffset = (int) (level.random.nextFloat() * 32 - 16);
            ScreenParticleBuilder.create(RegisterParticles.FIRE_RUNE_SCREEN, target)
                    .setLifetime(16)
                    .setColorData(ColorParticleData.create(colorPair.getA(), colorPair.getA()).build())
                    .setSpinData(spinDataBuilder.setSpinOffset(0.785f - 0.01f * gameTime % 6.28f).build())
                    .setScaleData(GenericParticleData.create(0.3F).build())
                    .addMotion(((ScreenParticleHandler.currentItemX - (double) xOffset / 2) - ScreenParticleHandler.currentItemX) / 10, ((ScreenParticleHandler.currentItemY + (double) yOffset / 2) - ScreenParticleHandler.currentItemY) / 10)
                    .spawnOnStack(xOffset, -yOffset);
        }
    }
}
