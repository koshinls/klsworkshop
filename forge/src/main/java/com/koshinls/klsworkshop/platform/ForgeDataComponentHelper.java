package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.Constants;
import com.koshinls.klsworkshop.platform.services.IDataComponentHelper;
import com.koshinls.klsworkshop.registry.RegistryEntry;
import com.koshinls.klsworkshop.registry.SimpleRegistryEntry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ForgeDataComponentHelper implements IDataComponentHelper {

    private static final DeferredRegister<DataComponentType<?>> COMPONENTS =
            DeferredRegister.create(
                    Registries.DATA_COMPONENT_TYPE,
                    Constants.MOD_ID
            );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> RegistryEntry<DataComponentType<T>> register(
            String name,
            Supplier<DataComponentType<T>> supplier
    ) {

        var object = COMPONENTS.register(name, supplier);

        return new SimpleRegistryEntry<>(
                () -> (DataComponentType<T>) object.get()
        );
    }
}