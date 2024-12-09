package net.backupcup.hexed.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;

public class MaledictionEssenceBlock extends FallingBlock {
    public static final MapCodec<MaledictionEssenceBlock> CODEC = simpleCodec(MaledictionEssenceBlock::new);

    public MaledictionEssenceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {return CODEC;}
}
