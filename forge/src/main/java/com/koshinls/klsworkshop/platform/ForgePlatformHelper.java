package com.koshinls.klsworkshop.platform;

import com.koshinls.klsworkshop.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import com.koshinls.klsworkshop.settings.SettingsScreen;
import net.minecraft.client.Minecraft;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
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