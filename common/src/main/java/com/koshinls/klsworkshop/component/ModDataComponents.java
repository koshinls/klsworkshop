package com.koshinls.klsworkshop.component;

import com.koshinls.klsworkshop.platform.Services;
import com.koshinls.klsworkshop.registry.RegistryEntry;
import com.koshinls.klsworkshop.wrenchmodes.DebugModeState;
import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;
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

    public static final RegistryEntry<DataComponentType<DebugModeState>> DEBUG_MODE_STATE =
            Services.DATA_COMPONENTS.register(
                    "debug_mode_state",
                    () -> DataComponentType.<DebugModeState>builder()
                            .persistent(DebugModeState.CODEC)
                            .networkSynchronized(DebugModeState.STREAM_CODEC)
                            .build()
            );

    public static void init() {
    }
}