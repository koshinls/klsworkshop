package com.koshinls.klsworkshop.mixin.client;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.settings.SettingsState;
import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    @Inject(
            method = "getMarkerParticleTarget",
            at = @At("HEAD"),
            cancellable = true
    )
    private void klsworkshop$lightModeMarker(
            CallbackInfoReturnable<Block> cir
    ) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        if (minecraft.gameMode == null) {
            return;
        }

        if (!SettingsState.isEnabled(WrenchMode.LIGHT)) {
            return;
        }

        if (!(minecraft.player.getMainHandItem().getItem()
                instanceof CreativeWrenchItem)) {
            return;
        }

        if (CreativeWrenchItem.getMode(
                minecraft.player.getMainHandItem()
        ) != WrenchMode.LIGHT) {
            return;
        }

        cir.setReturnValue(Blocks.LIGHT);
    }
}