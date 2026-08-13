package com.koshinls.klsworkshop.wrenchmodes;

import com.koshinls.klsworkshop.settings.SettingsState;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WrenchModeHandler {

    public static void use(
            WrenchMode mode,
            Level level,
            Player player,
            InteractionHand hand
    ) {

        if (!SettingsState.isEnabled(mode))
            return;

        switch (mode) {

            case DEBUG ->
                    DebugMode.use(level, player, hand);

            case LIGHT ->
                    LightMode.use(level, player, hand);

            case COPY_PASTE ->
                    CopyPasteMode.use(level, player, hand);

            case SETTINGS ->
                    SettingsMode.use(level, player, hand);

            case BUILD ->
                    BuildMode.use(level, player, hand);

            case TERRAFORM ->
                    TerraformMode.use(level, player, hand);

            case NBT ->
                    NbtMode.use(level, player, hand);

            case UTILITY ->
                    UtilityMode.use(level, player, hand);

            default ->
                    player.displayClientMessage(
                            Component.literal("Unknown mode!"),
                            true
                    );
        }
    }

    public static void attack(
            WrenchMode mode,
            Level level,
            Player player,
            InteractionHand hand,
            BlockPos pos,
            BlockState state
    ) {

        if (!SettingsState.isEnabled(mode))
            return;

        player.displayClientMessage(
                Component.literal("Left-click!"),
                true
        );
    }
}