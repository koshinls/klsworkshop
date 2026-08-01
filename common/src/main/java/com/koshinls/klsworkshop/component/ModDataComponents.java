package com.koshinls.klsworkshop.component;

import com.koshinls.klsworkshop.platform.Services;
import com.koshinls.klsworkshop.registry.RegistryEntry;
import net.minecraft.core.component.DataComponentType;

public class ModDataComponents {

    public static final RegistryEntry<DataComponentType<WrenchMode>> WRENCH_MODE =
            Services.DATA_COMPONENTS.register(
                    "wrench_mode",
                    () -> DataComponentType.<WrenchMode>builder()
                            .persistent(WrenchMode.CODEC)
                            .networkSynchronized(WrenchMode.STREAM_CODEC)
                            .build()
            );

    public static void init() {
    }
}