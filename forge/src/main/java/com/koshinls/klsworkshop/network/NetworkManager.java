package com.koshinls.klsworkshop.network;

import com.koshinls.klsworkshop.Constants;
import com.koshinls.klsworkshop.network.packet.NextModePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class NetworkManager {

    public static final SimpleChannel CHANNEL =
            ChannelBuilder
                    .named(ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            "main"
                    ))
                    .networkProtocolVersion(1)
                    .simpleChannel()
                    .play()
                    .serverbound()
                    .add(
                            NextModePacket.class,
                            NextModePacket.STREAM_CODEC,
                            ForgeNetworkHandler::handleNextMode
                    )
                    .build();

    public static void init() {
    }

    public static void sendToServer(NextModePacket packet) {
        CHANNEL.send(packet, PacketDistributor.SERVER.noArg());
    }
}