package com.koshinls.klsworkshop.platform.services;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

import com.koshinls.klsworkshop.registry.RegistryEntry;

public interface IRegistryHelper {

    RegistryEntry<Item> registerItem(
            String name,
            Supplier<Item> supplier
    );

}