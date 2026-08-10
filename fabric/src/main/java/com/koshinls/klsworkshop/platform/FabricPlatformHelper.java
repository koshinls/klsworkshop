package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import com.koshinls.klsworkshop.settings.SettingsScreen;
import net.minecraft.client.Minecraft;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public void openSettingsScreen() {

        Minecraft minecraft = Minecraft.getInstance();

        minecraft.setScreen(
                new SettingsScreen(minecraft.screen)
        );
    }
}
