package com.koshinls.klsworkshop;

import com.koshinls.klsworkshop.platform.ForgeCreativeTabHelper;
import com.koshinls.klsworkshop.platform.ForgeDataComponentHelper;
import com.koshinls.klsworkshop.platform.ForgeRegistryHelper;
import com.koshinls.klsworkshop.platform.Services;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ExampleMod {

    public ExampleMod() {

        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        ForgeRegistryHelper.register(bus);
        ForgeCreativeTabHelper.register(bus);
        ForgeDataComponentHelper.register(bus);

        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
        Services.INPUT.init();
    }
}