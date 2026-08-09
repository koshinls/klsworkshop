package com.koshinls.klsworkshop.item;

import com.koshinls.klsworkshop.component.ModDataComponents;
import com.koshinls.klsworkshop.wrenchmodes.DebugMode;
import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;
import com.koshinls.klsworkshop.wrenchmodes.WrenchModeHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class CreativeWrenchItem extends Item {

    public CreativeWrenchItem(Properties properties) {
        super(properties);
    }

    public static WrenchMode getMode(ItemStack stack) {
        WrenchMode mode = stack.get(ModDataComponents.WRENCH_MODE.get());
        return mode == null ? WrenchMode.DEBUG : mode;
    }

    public static void setMode(ItemStack stack, WrenchMode mode) {
        stack.set(ModDataComponents.WRENCH_MODE.get(), mode);
    }

    public static void nextMode(ItemStack stack) {
        setMode(stack, getMode(stack).next());
    }

    public static void previousMode(ItemStack stack) {
        setMode(stack, getMode(stack).previous());
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        super.appendHoverText(stack, context, tooltip, flag);

        WrenchMode mode = getMode(stack);

        tooltip.add(Component.empty());
        tooltip.add(mode.getDisplayName());
        tooltip.add(mode.getDescription());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand
    ) {

        ItemStack stack = player.getItemInHand(hand);

        WrenchModeHandler.use(
                getMode(stack),
                level,
                player,
                hand
        );

        return InteractionResultHolder.success(stack);



    }

    @Override
    public boolean canAttackBlock(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player
    ) {
        if (!level.isClientSide()
                && getMode(player.getMainHandItem()) == WrenchMode.DEBUG) {

            DebugMode.selectProperty(
                    level,
                    player,
                    InteractionHand.MAIN_HAND,
                    pos,
                    state
            );

            return false;
        }

        return super.canAttackBlock(state, level, pos, player);
    }
}