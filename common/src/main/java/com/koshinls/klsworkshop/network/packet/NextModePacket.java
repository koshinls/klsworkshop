package com.koshinls.klsworkshop.network.packet;

import com.koshinls.klsworkshop.Constants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record NextModePacket(boolean forward) implements CustomPacketPayload {

    public static final Type<NextModePacket> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            "next_mode"
                    )
            );

    public static final StreamCodec<
            net.minecraft.network.RegistryFriendlyByteBuf,
            NextModePacket
            > STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.BOOL,
                    NextModePacket::forward,
                    NextModePacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}