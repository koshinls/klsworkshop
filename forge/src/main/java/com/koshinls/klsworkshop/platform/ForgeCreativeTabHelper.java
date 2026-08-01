package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.Constants;
import com.koshinls.klsworkshop.platform.services.ICreativeTabHelper;
import com.koshinls.klsworkshop.registry.RegistryEntry;
import com.koshinls.klsworkshop.registry.SimpleRegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ForgeCreativeTabHelper implements ICreativeTabHelper {

    // <-- PUT IT HERE
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(
                    Registries.CREATIVE_MODE_TAB,
                    Constants.MOD_ID
            );

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }

    @Override
    public RegistryEntry<CreativeModeTab> registerTab(
            String name,
            Supplier<CreativeModeTab> supplier
    ) {

        var object = TABS.register(name, supplier);

        return new SimpleRegistryEntry<>(object::get);
    }
}