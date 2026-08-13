package com.koshinls.klsworkshop.wrenchmodes;

import com.koshinls.klsworkshop.settings.SettingsState;
import com.koshinls.klsworkshop.settings.screens.LightSettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class LightMode {

    public static void use(
            Level level,
            Player player,
            InteractionHand hand
    ) {

        if (level.isClientSide()) {
            return;
        }

        HitResult hit = player.pick(
                20.0D,
                0.0F,
                false
        );

        if (hit.getType() != HitResult.Type.BLOCK) {

            player.displayClientMessage(
                    Component.literal("No block targeted."),
                    true
            );

            return;
        }

        BlockHitResult blockHit =
                (BlockHitResult) hit;

        BlockPos hitPos =
                blockHit.getBlockPos();

        BlockPos placePos =
                hitPos.relative(
                        blockHit.getDirection()
                );

        BlockState lightState =
                Blocks.LIGHT.defaultBlockState()
                        .setValue(
                                LightBlock.LEVEL,
                                SettingsState.lightLevel
                        );

        if (level.getBlockState(placePos).isAir()) {

            level.setBlock(
                    placePos,
                    lightState,
                    3
            );

            player.displayClientMessage(
                    Component.literal(
                            "Light placed: " +
                                    SettingsState.lightLevel
                    ),
                    true
            );
        }
    }

    public static void attack(
            Level level,
            Player player,
            InteractionHand hand,
            BlockPos pos,
            BlockState state
    ) {

        if (!level.isClientSide()) {
            return;
        }

        Minecraft minecraft =
                Minecraft.getInstance();

        minecraft.setScreen(
                new LightSettingsScreen(
                        minecraft.screen
                )
        );
    }
}