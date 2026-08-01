package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.Constants;
import com.koshinls.klsworkshop.platform.services.IRegistryHelper;
import com.koshinls.klsworkshop.registry.RegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class FabricRegistryHelper implements IRegistryHelper {

    @Override
    public RegistryEntry<Item> registerItem(String name, Supplier<Item> supplier) {

        Item item = Registry.register(
                BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
                supplier.get()
        );

        return new FabricRegistryEntry(item);
    }

    private static class FabricRegistryEntry implements RegistryEntry<Item> {

        private final Item item;

        private FabricRegistryEntry(Item item) {
            this.item = item;
        }

        @Override
        public Item get() {
            return item;
        }
    }
}