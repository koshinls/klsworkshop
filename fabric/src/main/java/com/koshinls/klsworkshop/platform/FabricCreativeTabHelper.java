package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.platform.services.ICreativeTabHelper;
import com.koshinls.klsworkshop.registry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

import com.koshinls.klsworkshop.Constants;
import com.koshinls.klsworkshop.registry.SimpleRegistryEntry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class FabricCreativeTabHelper implements ICreativeTabHelper {

    @Override
    public RegistryEntry<CreativeModeTab> registerTab(
            String name,
            Supplier<CreativeModeTab> supplier
    ) {

        CreativeModeTab tab = Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
                supplier.get()
        );

        return new SimpleRegistryEntry<>(() -> tab);
    }
}
