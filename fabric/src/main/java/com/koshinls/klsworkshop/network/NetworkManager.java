package com.koshinls.klsworkshop.network;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.network.packet.NextModePacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.item.ItemStack;

public class NetworkManager {

    public static void init() {

        PayloadTypeRegistry.playC2S().register(
                NextModePacket.TYPE,
                NextModePacket.STREAM_CODEC
        );

        ServerPlayNetworking.registerGlobalReceiver(
                NextModePacket.TYPE,
                (packet, context) -> {

                    context.server().execute(() -> {

                        var player = context.player();

                        ItemStack stack = player.getMainHandItem();

                        if (!(stack.getItem() instanceof CreativeWrenchItem))
                            return;

                        if (packet.forward()) {
                            CreativeWrenchItem.nextMode(stack);
                        } else {
                            CreativeWrenchItem.previousMode(stack);
                        }
                    });
                }
        );
    }

    public static void sendToServer(NextModePacket packet) {
        ClientPlayNetworking.send(packet);
    }
}