package com.koshinls.klsworkshop;

import com.koshinls.klsworkshop.platform.ForgeRegistryHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ExampleMod {

    public ExampleMod() {

        ForgeRegistryHelper.register(
                FMLJavaModLoadingContext.get().getModEventBus()
        );

        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
    }
}