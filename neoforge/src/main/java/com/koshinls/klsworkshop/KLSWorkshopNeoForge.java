package com.koshinls.klsworkshop;

import com.koshinls.klsworkshop.platform.NeoForgeDataComponentHelper;
import com.koshinls.klsworkshop.platform.NeoForgeRegistryHelper;
import com.koshinls.klsworkshop.platform.Services;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import com.koshinls.klsworkshop.platform.NeoForgeCreativeTabHelper;
import com.koshinls.klsworkshop.client.ModeInputHandler;

@Mod(Constants.MOD_ID)
public class KLSWorkshopNeoForge {

    public KLSWorkshopNeoForge(IEventBus eventBus) {

        NeoForgeRegistryHelper.register(eventBus);
        NeoForgeCreativeTabHelper.register(eventBus);
        NeoForgeDataComponentHelper.register(eventBus);
        ModeInputHandler.register();

        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();
        Services.INPUT.init();
    }
}