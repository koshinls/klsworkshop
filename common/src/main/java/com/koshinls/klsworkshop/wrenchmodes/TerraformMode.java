package com.koshinls.klsworkshop.wrenchmodes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TerraformMode {

    public static void use(Level level, Player player, InteractionHand hand) {

        player.displayClientMessage(
                Component.literal("For later!"),
                true
        );
    }
}