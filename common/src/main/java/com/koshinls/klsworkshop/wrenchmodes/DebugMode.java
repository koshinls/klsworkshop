package com.koshinls.klsworkshop.wrenchmodes;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

public class DebugMode {
    public static void use(
            Level level,
            Player player,
            InteractionHand hand
    ) {

        System.out.println("DebugMode.use()");

        if (level.isClientSide())
            return;

        if (level.isClientSide())
            return;

        var hit = player.pick(5.0D, 0.0F, false);

        if (!(hit instanceof BlockHitResult blockHit))
            return;

        var pos = blockHit.getBlockPos();
        BlockState state = level.getBlockState(pos);

        if (!state.hasProperty(BlockStateProperties.FACING)) {

            player.displayClientMessage(
                    Component.literal("No facing property"),
                    true
            );

            return;
        }

        Direction current = state.getValue(BlockStateProperties.FACING);

        Direction next = switch (current) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
        };

        level.setBlock(
                pos,
                state.setValue(BlockStateProperties.FACING, next),
                3
        );

        player.displayClientMessage(
                Component.literal("Facing: " + next.getName()),
                true
        );
    }
}