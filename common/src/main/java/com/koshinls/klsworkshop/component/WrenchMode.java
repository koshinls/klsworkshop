package com.koshinls.klsworkshop.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public enum WrenchMode {

    DEBUG,
    TERRAFORM,
    BUILD,
    COPY_PASTE,
    LIGHT,
    NBT,
    SETTINGS;

    public static final Codec<WrenchMode> CODEC =
            Codec.STRING.xmap(
                    WrenchMode::valueOf,
                    WrenchMode::name
            );

    public static final StreamCodec<ByteBuf, WrenchMode> STREAM_CODEC =
            ByteBufCodecs.fromCodec(CODEC);

    public WrenchMode next() {
        return values()[(ordinal() + 1) % values().length];
    }

    public WrenchMode previous() {
        return values()[(ordinal() - 1 + values().length) % values().length];
    }
}