package com.koshinls.klsworkshop.network;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.network.packet.NextModePacket;
import com.koshinls.klsworkshop.network.packet.ShowModePacket;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ForgeNetworkHandler {

    public static void handleNextMode(
            NextModePacket packet,
            CustomPayloadEvent.Context context
    ) {

        context.enqueueWork(() -> {

            var player = context.getSender();

            if (player == null)
                return;

            ItemStack stack = player.getMainHandItem();

            if (!(stack.getItem() instanceof CreativeWrenchItem))
                return;

            if (packet.forward()) {
                CreativeWrenchItem.nextMode(stack);
            } else {
                CreativeWrenchItem.previousMode(stack);
            }
        });

        context.setPacketHandled(true);
    }

    public static void handleShowMode(
            ShowModePacket packet,
            CustomPayloadEvent.Context context
    ) {

        context.enqueueWork(() -> {

            var player = context.getSender();

            if (player == null)
                return;

            ItemStack stack = player.getMainHandItem();

            if (!(stack.getItem() instanceof CreativeWrenchItem))
                return;

            player.displayClientMessage(
                    CreativeWrenchItem.getMode(stack).getDisplayName(),
                    true
            );
        });

        context.setPacketHandled(true);
    }
}