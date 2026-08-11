package com.koshinls.klsworkshop.wrenchmodes;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public enum WrenchMode {

    DEBUG(
            Component.literal("Debug")
                    .withStyle(ChatFormatting.RED),
            Component.literal("Rotate and edit block states.")
                    .withStyle(ChatFormatting.RED)
    ),

    TERRAFORM(
            Component.literal("Terraform")
                    .withStyle(ChatFormatting.YELLOW),
            Component.literal("Shape the terrain.")
                    .withStyle(ChatFormatting.YELLOW)
    ),

    BUILD(
            Component.literal("Build")
                    .withStyle(ChatFormatting.GREEN),
            Component.literal("Advanced building tools.")
                    .withStyle(ChatFormatting.GREEN)
    ),

    COPY_PASTE(
            Component.literal("Copy/Paste")
                    .withStyle(ChatFormatting.AQUA),
            Component.literal("Copy and place structures.")
                    .withStyle(ChatFormatting.AQUA)
    ),

    LIGHT(
            Component.literal("Light")
                    .withStyle(ChatFormatting.BLUE),
            Component.literal("Lighting tools.")
                    .withStyle(ChatFormatting.BLUE)
    ),

    NBT(
            Component.literal("NBT")
                    .withStyle(ChatFormatting.DARK_PURPLE),
            Component.literal("Inspect and edit NBT.")
                    .withStyle(ChatFormatting.DARK_PURPLE)
    ),

    UTILITY(
            Component.literal("Utility")
                    .setStyle(Style.EMPTY.withColor(0xFF00FF)),
            Component.literal("Developer and utility tools.")
                    .setStyle(Style.EMPTY.withColor(0xFF00FF))
    ),

    SETTINGS(
            Component.literal("Settings")
                    .withStyle(ChatFormatting.WHITE),
            Component.literal("Configure the wrench.")
                    .withStyle(ChatFormatting.WHITE)
    );

    public static final Codec<WrenchMode> CODEC =
            Codec.STRING.xmap(
                    WrenchMode::valueOf,
                    WrenchMode::name
            );

    public static final StreamCodec<ByteBuf, WrenchMode> STREAM_CODEC =
            ByteBufCodecs.fromCodec(CODEC);

    private final Component displayName;
    private final Component description;

    WrenchMode(Component displayName, Component description) {
        this.displayName = displayName;
        this.description = description;
    }

    public Component getDisplayName() {
        return displayName;
    }

    public Component getDescription() {
        return description;
    }

    public WrenchMode next() {
        return values()[(ordinal() + 1) % values().length];
    }

    public WrenchMode previous() {
        return values()[(ordinal() - 1 + values().length) % values().length];
    }
}