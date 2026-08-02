package com.koshinls.klsworkshop.client;

import com.koshinls.klsworkshop.Constants;

public final class ModeInputHandler {

    private ModeInputHandler() {
    }

    public static void register() {
        Constants.LOG.info("ModeInputHandler registered.");
    }
}