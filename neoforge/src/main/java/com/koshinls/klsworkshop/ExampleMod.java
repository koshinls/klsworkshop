package com.koshinls.klsworkshop;

import com.koshinls.klsworkshop.platform.NeoForgeDataComponentHelper;
import com.koshinls.klsworkshop.platform.NeoForgeRegistryHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import com.koshinls.klsworkshop.platform.NeoForgeCreativeTabHelper;

@Mod(Constants.MOD_ID)
public class ExampleMod {

    public ExampleMod(IEventBus eventBus) {

        NeoForgeRegistryHelper.register(eventBus);
        NeoForgeCreativeTabHelper.register(eventBus);
        NeoForgeDataComponentHelper.register(eventBus);

        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();
    }
}