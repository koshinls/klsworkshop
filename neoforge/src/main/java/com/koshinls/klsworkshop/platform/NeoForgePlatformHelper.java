package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.platform.services.IPlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import com.koshinls.klsworkshop.settings.SettingsScreen;
import net.minecraft.client.Minecraft;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public void openSettingsScreen() {

        Minecraft minecraft = Minecraft.getInstance();

        minecraft.setScreen(
                new SettingsScreen(minecraft.screen)
        );
    }
}