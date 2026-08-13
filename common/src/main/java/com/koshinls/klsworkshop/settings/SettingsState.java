package com.koshinls.klsworkshop.settings;

import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;

public class SettingsState {

    public static boolean masterEnabled = true;

    public static boolean debugEnabled = true;
    public static boolean terraformEnabled = true;
    public static boolean buildEnabled = true;
    public static boolean copyPasteEnabled = true;
    public static boolean lightEnabled = true;
    public static boolean nbtEnabled = true;
    public static boolean utilityEnabled = true;

    public static int lightLevel = 15;

    /**
     * Returns whether a mode is actually available right now.
     *
     * The master switch disables every mode except SETTINGS,
     * without changing the individual mode settings.
     */
    public static boolean isEnabled(WrenchMode mode) {

        if (mode == WrenchMode.SETTINGS) {
            return true;
        }

        if (!masterEnabled) {
            return false;
        }

        return getStoredEnabled(mode);
    }

    /**
     * Returns the individual setting without applying the master switch.
     *
     * This is what the settings GUIs use when changing a mode.
     */
    public static boolean getStoredEnabled(WrenchMode mode) {

        return switch (mode) {
            case DEBUG -> debugEnabled;
            case TERRAFORM -> terraformEnabled;
            case BUILD -> buildEnabled;
            case COPY_PASTE -> copyPasteEnabled;
            case LIGHT -> lightEnabled;
            case NBT -> nbtEnabled;
            case UTILITY -> utilityEnabled;
            case SETTINGS -> true;
        };
    }

    /**
     * Changes only the individual mode setting.
     *
     * The master switch is never changed here.
     */
    public static void setStoredEnabled(
            WrenchMode mode,
            boolean enabled
    ) {

        switch (mode) {

            case DEBUG ->
                    debugEnabled = enabled;

            case TERRAFORM ->
                    terraformEnabled = enabled;

            case BUILD ->
                    buildEnabled = enabled;

            case COPY_PASTE ->
                    copyPasteEnabled = enabled;

            case LIGHT ->
                    lightEnabled = enabled;

            case NBT ->
                    nbtEnabled = enabled;

            case UTILITY ->
                    utilityEnabled = enabled;

            case SETTINGS -> {
                // Settings is always available.
            }
        }
    }
}