package com.koshinls.klsworkshop.component;

public enum WrenchMode {

    DEBUG,
    TERRAFORM,
    BUILD,
    COPY_PASTE,
    LIGHT,
    NBT,
    SETTINGS;

    public WrenchMode next() {
        return values()[(ordinal() + 1) % values().length];
    }

    public WrenchMode previous() {
        return values()[(ordinal() - 1 + values().length) % values().length];
    }
}