package com.koshinls.klsworkshop.registry;

import com.koshinls.klsworkshop.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {

    public static final RegistryEntry<CreativeModeTab> MAIN_TAB =
            Services.CREATIVE_TAB.registerTab(
                    "main",
                    () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                            .title(Component.literal("KLS Workshop"))
                            .icon(() -> new ItemStack(ModItems.CREATIVE_WRENCH.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.CREATIVE_WRENCH.get());
                            })
                            .build()
            );

    public static void init() {
    }
}