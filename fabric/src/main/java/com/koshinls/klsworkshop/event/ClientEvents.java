package com.koshinls.klsworkshop.event;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.network.NetworkManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class ClientEvents {

    private static int lastSlot = -1;

    public static void register() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.player == null)
                return;

            int slot = client.player.getInventory().selected;

            if (slot == lastSlot)
                return;

            lastSlot = slot;

            if (!(client.player.getMainHandItem().getItem()
                    instanceof CreativeWrenchItem))
                return;

            NetworkManager.showCurrentMode();
        });
    }
}