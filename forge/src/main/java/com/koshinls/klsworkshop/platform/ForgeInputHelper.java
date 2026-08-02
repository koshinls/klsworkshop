package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.platform.services.IInputHelper;

public class ForgeInputHelper implements IInputHelper {

    @Override
    public void init() {
        // Nothing to do.
        // @Mod.EventBusSubscriber registers ClientEvents automatically.
    }
}