package com.koshinls.klsworkshop.settings;

import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;

public class SettingsState {

    public static boolean isEnabled(WrenchMode mode) {

        return switch (mode) {
            case DEBUG -> debugEnabled;
            case TERRAFORM -> terraformEnabled;
            case BUILD -> buildEnabled;
            case COPY_PASTE -> copyPasteEnabled;
            case LIGHT -> lightEnabled;
            case NBT -> nbtEnabled;
            case UTILITY -> utilityEnabled;

            // Settings itself should always be accessible.
            case SETTINGS -> true;
        };
    }

    public static boolean debugEnabled = true;
    public static boolean terraformEnabled = true;
    public static boolean buildEnabled = true;
    public static boolean copyPasteEnabled = true;
    public static boolean lightEnabled = true;
    public static boolean nbtEnabled = true;
    public static boolean utilityEnabled = true;
}
