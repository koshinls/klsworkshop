package com.koshinls.klsworkshop.mixin;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.network.packet.NextModePacket;
import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.koshinls.klsworkshop.network.NetworkManager;

@Mixin(MouseHandler.class)
public class MixinMouse {

    @Inject(
            method = "onScroll",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onScroll(
            long windowPointer,
            double xOffset,
            double yOffset,
            CallbackInfo ci
    ) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null)
            return;

        if (!minecraft.player.isShiftKeyDown())
            return;

        ItemStack stack = minecraft.player.getMainHandItem();

        if (!(stack.getItem() instanceof CreativeWrenchItem))
            return;

        WrenchMode current = CreativeWrenchItem.getMode(stack);

        boolean forward = yOffset > 0;

        WrenchMode next = forward
                ? current.next()
                : current.previous();

        minecraft.player.displayClientMessage(
                next.getDisplayName(),
                true
        );

        NetworkManager.sendToServer(
                new NextModePacket(forward)
        );

        ci.cancel();
    }
}