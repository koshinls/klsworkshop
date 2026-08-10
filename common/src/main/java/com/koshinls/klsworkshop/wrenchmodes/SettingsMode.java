package com.koshinls.klsworkshop.wrenchmodes;

import com.koshinls.klsworkshop.platform.Services;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SettingsMode {

    public static void use(
            Level level,
            Player player,
            InteractionHand hand
    ) {

        if (!level.isClientSide())
            return;

        Services.PLATFORM.openSettingsScreen();
    }
}