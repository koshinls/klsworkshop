package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.platform.services.IInputHelper;
import com.koshinls.klsworkshop.event.ClientEvents;

public class FabricInputHelper implements IInputHelper {

    @Override
    public void init() {
        ClientEvents.register();
    }
}