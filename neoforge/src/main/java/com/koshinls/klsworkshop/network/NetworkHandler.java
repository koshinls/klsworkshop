package com.koshinls.klsworkshop.network;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.network.packet.NextModePacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import static com.koshinls.klsworkshop.Constants.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {

        var registrar = event.registrar("1");

        registrar.playToServer(
                NextModePacket.TYPE,
                NextModePacket.STREAM_CODEC,
                (packet, context) -> {

                    var player = context.player();
                    var stack = player.getMainHandItem();

                    if (!(stack.getItem() instanceof CreativeWrenchItem))
                        return;

                    if (packet.forward()) {
                        CreativeWrenchItem.nextMode(stack);
                    } else {
                        CreativeWrenchItem.previousMode(stack);
                    }
                }
        );
    }
}