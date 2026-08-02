package com.koshinls.klsworkshop.item;

import com.koshinls.klsworkshop.component.ModDataComponents;
import com.koshinls.klsworkshop.component.WrenchMode;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

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

}