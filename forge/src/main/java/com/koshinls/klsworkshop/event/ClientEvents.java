package com.koshinls.klsworkshop.event;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.koshinls.klsworkshop.Constants.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ClientEvents {

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

        if (scroll > 0) {
            CreativeWrenchItem.nextMode(stack);
        } else if (scroll < 0) {
            CreativeWrenchItem.previousMode(stack);
        }

        player.displayClientMessage(
                CreativeWrenchItem.getMode(stack).getDisplayName(),
                true
        );

        event.setCanceled(true);
        // We'll change the mode here next.
    }
}
