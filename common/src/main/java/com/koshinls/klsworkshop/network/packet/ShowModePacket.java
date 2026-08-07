package com.koshinls.klsworkshop.network.packet;

import com.koshinls.klsworkshop.Constants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ShowModePacket() implements CustomPacketPayload {

    public static final Type<ShowModePacket> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            "show_mode"
                    )
            );

    public static final StreamCodec<
            RegistryFriendlyByteBuf,
            ShowModePacket
            > STREAM_CODEC =
            StreamCodec.unit(new ShowModePacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}