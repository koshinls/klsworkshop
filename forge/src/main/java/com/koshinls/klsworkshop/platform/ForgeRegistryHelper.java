package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.Constants;
import com.koshinls.klsworkshop.platform.services.IRegistryHelper;
import com.koshinls.klsworkshop.registry.RegistryEntry;
import com.koshinls.klsworkshop.registry.SimpleRegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ForgeRegistryHelper implements IRegistryHelper {

    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    @Override
    public RegistryEntry<Item> registerItem(String name, Supplier<Item> supplier) {

        var object = ITEMS.register(name, supplier);

        return new SimpleRegistryEntry<>(object::get);
    }
}