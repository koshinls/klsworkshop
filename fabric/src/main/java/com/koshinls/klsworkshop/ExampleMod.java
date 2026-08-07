package com.koshinls.klsworkshop;

import com.koshinls.klsworkshop.network.NetworkManager;
import com.koshinls.klsworkshop.platform.Services;
import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        NetworkManager.init();
        Services.INPUT.init();
    }
}
