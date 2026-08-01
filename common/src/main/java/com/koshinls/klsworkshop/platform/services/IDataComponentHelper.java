package com.koshinls.klsworkshop.platform.services;

import com.koshinls.klsworkshop.registry.RegistryEntry;
import net.minecraft.core.component.DataComponentType;

import java.util.function.Supplier;

public interface IDataComponentHelper {

    <T> RegistryEntry<DataComponentType<T>> register(
            String name,
            Supplier<DataComponentType<T>> supplier
    );
}
