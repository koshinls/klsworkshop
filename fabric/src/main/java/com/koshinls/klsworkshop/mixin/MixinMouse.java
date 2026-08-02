package com.koshinls.klsworkshop.mixin;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

        if (yOffset > 0) {
            CreativeWrenchItem.nextMode(stack);
        } else if (yOffset < 0) {
            CreativeWrenchItem.previousMode(stack);
        }

        minecraft.player.displayClientMessage(
                CreativeWrenchItem.getMode(stack).getDisplayName(),
                true
        );

        ci.cancel();
    }
}