package com.koshinls.klsworkshop.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public enum WrenchMode {

    DEBUG("Debug"),
    TERRAFORM("Terraform"),
    BUILD("Build"),
    COPY_PASTE("Copy/Paste"),
    LIGHT("Light"),
    NBT("NBT"),
    SETTINGS("Settings");

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

    private final String displayName;

    WrenchMode(String displayName) {
        this.displayName = displayName;
    }

    public Component getDisplayName() {
        return switch (this) {
            case DEBUG ->
                    Component.literal("Debug")
                            .withStyle(ChatFormatting.RED);

            case TERRAFORM ->
                    Component.literal("Terraform")
                            .withStyle(ChatFormatting.YELLOW);

            case BUILD ->
                    Component.literal("Build")
                            .withStyle(ChatFormatting.GREEN);

            case COPY_PASTE ->
                    Component.literal("Copy/Paste")
                            .withStyle(ChatFormatting.AQUA);

            case LIGHT ->
                    Component.literal("Light")
                            .withStyle(ChatFormatting.BLUE);

            case NBT ->
                    Component.literal("NBT")
                            .setStyle(Style.EMPTY.withColor(0xFF00FF));

            case SETTINGS ->
                    Component.literal("Settings")
                            .withStyle(ChatFormatting.WHITE);
        };
    }
}