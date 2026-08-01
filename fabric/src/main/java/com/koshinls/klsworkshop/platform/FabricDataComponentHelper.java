package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.Constants;
import com.koshinls.klsworkshop.platform.services.IDataComponentHelper;
import com.koshinls.klsworkshop.registry.RegistryEntry;
import com.koshinls.klsworkshop.registry.SimpleRegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class FabricDataComponentHelper implements IDataComponentHelper {

    @Override
    public <T> RegistryEntry<DataComponentType<T>> register(
            String name,
            Supplier<DataComponentType<T>> supplier
    ) {

        DataComponentType<T> component = supplier.get();

        Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
                component
        );

        return new SimpleRegistryEntry<>(() -> component);
    }
}