package com.koshinls.klsworkshop.registry;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.platform.Services;
import net.minecraft.world.item.Item;

public class ModItems {

    public static final RegistryEntry<Item> CREATIVE_WRENCH =
            Services.REGISTRY.registerItem(
                    "creative_wrench",
                    () -> new CreativeWrenchItem(new Item.Properties())
            );

    public static void init() {
        // Forces the class to load.
    }
}