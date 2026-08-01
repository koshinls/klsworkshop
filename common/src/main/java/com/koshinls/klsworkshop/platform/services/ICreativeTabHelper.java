package com.koshinls.klsworkshop.platform.services;

import com.koshinls.klsworkshop.registry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public interface ICreativeTabHelper {

    RegistryEntry<CreativeModeTab> registerTab(
            String name,
            Supplier<CreativeModeTab> supplier
    );

}
