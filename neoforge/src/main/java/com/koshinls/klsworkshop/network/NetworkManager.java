package com.koshinls.klsworkshop.network;

import com.koshinls.klsworkshop.network.packet.NextModePacket;
import net.neoforged.neoforge.network.PacketDistributor;

public class NetworkManager {

    public static void init() {
    }

    public static void sendToServer(NextModePacket packet) {
        PacketDistributor.sendToServer(packet);
    }
}