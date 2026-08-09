package com.koshinls.klsworkshop.wrenchmodes;

import com.koshinls.klsworkshop.component.ModDataComponents;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Collection;

public class DebugMode {

    public static void use(
            Level level,
            Player player,
            InteractionHand hand
    ) {

        if (level.isClientSide())
            return;

        if (!player.canUseGameMasterBlocks()) {
            player.displayClientMessage(
                    Component.literal("Requires OP"),
                    true
            );
            return;
        }

        var hit = player.pick(5.0D, 0.0F, false);

        if (!(hit instanceof BlockHitResult blockHit))
            return;

        BlockPos pos = blockHit.getBlockPos();
        BlockState state = level.getBlockState(pos);

        ItemStack stack = player.getItemInHand(hand);

        Collection<Property<?>> properties =
                state.getProperties();

        if (properties.isEmpty()) {

            message(
                    player,
                    Component.literal("No properties")
            );

            return;
        }

        /*
         * TEMPORARY
         *
         * Until we make DebugModeState,
         * always edit the first property.
         */

        var blockId =
                state.getBlockHolder()
                        .unwrapKey()
                        .orElseThrow()
                        .location();

        DebugModeState debugState =
                stack.get(ModDataComponents.DEBUG_MODE_STATE.get());

        if (debugState == null)
            debugState = DebugModeState.EMPTY;

        String propertyName =
                debugState.get(blockId);

        Property<?> property = null;

        if (propertyName != null) {

            for (Property<?> p : properties) {

                if (p.getName().equals(propertyName)) {
                    property = p;
                    break;
                }
            }
        }

        if (property == null)
            property = properties.iterator().next();

        BlockState newState =
                cycleState(
                        state,
                        property,
                        player.isShiftKeyDown()
                );

        level.setBlock(
                pos,
                newState,
                18
        );

        message(
                player,
                Component.literal(
                        property.getName()
                                + " = "
                                + getName(newState, property)
                )
        );
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>>
    BlockState cycleState(
            BlockState state,
            Property<?> property,
            boolean backwards
    ) {

        Property<T> typed =
                (Property<T>) property;

        T value =
                typed.getValue(
                                state.getValue(typed)
                                        .toString()
                        )
                        .orElse(state.getValue(typed));

        T next =
                getRelative(
                        typed.getPossibleValues(),
                        value,
                        backwards
                );

        return state.setValue(
                typed,
                next
        );
    }

    private static <T>
    T getRelative(
            Iterable<T> values,
            T current,
            boolean backwards
    ) {

        return backwards
                ? Util.findPreviousInIterable(
                values,
                current
        )
                : Util.findNextInIterable(
                values,
                current
        );
    }

    private static void message(
            Player player,
            Component message
    ) {

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(
                    message,
                    true
            );
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>>
    String getName(
            BlockState state,
            Property<?> property
    ) {

        Property<T> typed =
                (Property<T>) property;

        return typed.getName(
                state.getValue(typed)
        );
    }

    public static void selectProperty(
            Level level,
            Player player,
            InteractionHand hand,
            BlockPos pos,
            BlockState state
    ) {
        if (level.isClientSide())
            return;

        if (!player.canUseGameMasterBlocks()) {
            message(
                    player,
                    Component.literal("Requires OP")
            );
            return;
        }

        ItemStack stack = player.getItemInHand(hand);

        Collection<Property<?>> properties =
                state.getProperties();

        if (properties.isEmpty()) {
            message(
                    player,
                    Component.literal("No properties")
            );
            return;
        }

        var blockId =
                state.getBlockHolder()
                        .unwrapKey()
                        .orElseThrow()
                        .location();

        DebugModeState debugState =
                stack.get(ModDataComponents.DEBUG_MODE_STATE.get());

        if (debugState == null)
            debugState = DebugModeState.EMPTY;

        String propertyName =
                debugState.get(blockId);

        Property<?> current = null;

        if (propertyName != null) {
            for (Property<?> property : properties) {
                if (property.getName().equals(propertyName)) {
                    current = property;
                    break;
                }
            }
        }

        Property<?> next =
                getRelative(
                        properties,
                        current,
                        player.isShiftKeyDown()
                );

        debugState = debugState.with(
                blockId,
                next.getName()
        );

        stack.set(
                ModDataComponents.DEBUG_MODE_STATE.get(),
                debugState
        );

        message(
                player,
                Component.literal(
                        "Selected "
                                + next.getName()
                                + " = "
                                + getName(state, next)
                )
        );
    }
}
