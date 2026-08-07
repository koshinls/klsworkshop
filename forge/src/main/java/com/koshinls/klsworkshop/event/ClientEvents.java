package com.koshinls.klsworkshop.event;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.network.NetworkManager;
import com.koshinls.klsworkshop.network.packet.NextModePacket;
import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.koshinls.klsworkshop.Constants.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ClientEvents {

    private static int lastSlot = -1;

    @SubscribeEvent
    public static void mouseScroll(InputEvent.MouseScrollingEvent event) {

        var player = Minecraft.getInstance().player;

        if (player == null)
            return;

        if (!player.isShiftKeyDown())
            return;

        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof CreativeWrenchItem))
            return;

        double scroll = event.getDeltaY();

        WrenchMode current = CreativeWrenchItem.getMode(stack);

        boolean forward = scroll > 0;

        WrenchMode next = forward
                ? current.next()
                : current.previous();

        player.displayClientMessage(
                next.getDisplayName(),
                true
        );

        NetworkManager.sendToServer(
                new NextModePacket(forward)
        );

        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {

        if (event.phase != TickEvent.Phase.END)
            return;

        var player = Minecraft.getInstance().player;

        if (player == null)
            return;

        int slot = player.getInventory().selected;

        if (slot == lastSlot)
            return;

        lastSlot = slot;

        if (!(player.getMainHandItem().getItem() instanceof CreativeWrenchItem))
            return;

        NetworkManager.showCurrentMode();
    }
}
