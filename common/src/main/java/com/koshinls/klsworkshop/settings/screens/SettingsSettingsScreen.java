package com.koshinls.klsworkshop.settings.screens;

import com.koshinls.klsworkshop.settings.SettingsState;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SettingsSettingsScreen extends Screen {

    private final Screen parent;

    public SettingsSettingsScreen(Screen parent) {
        super(Component.literal("Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {

        this.addRenderableWidget(
                Button.builder(
                                getAllModesButtonText(),
                                button -> {

                                    boolean allEnabled =
                                            SettingsState.debugEnabled
                                                    && SettingsState.terraformEnabled
                                                    && SettingsState.buildEnabled
                                                    && SettingsState.copyPasteEnabled
                                                    && SettingsState.lightEnabled
                                                    && SettingsState.nbtEnabled
                                                    && SettingsState.utilityEnabled;

                                    boolean newState = !allEnabled;

                                    SettingsState.debugEnabled = newState;
                                    SettingsState.terraformEnabled = newState;
                                    SettingsState.buildEnabled = newState;
                                    SettingsState.copyPasteEnabled = newState;
                                    SettingsState.lightEnabled = newState;
                                    SettingsState.nbtEnabled = newState;
                                    SettingsState.utilityEnabled = newState;

                                    button.setMessage(
                                            getAllModesButtonText()
                                    );
                                }
                        )
                        .bounds(
                                this.width / 2 - 100,
                                80,
                                200,
                                20
                        )
                        .build()
        );

        this.addRenderableWidget(
                Button.builder(
                                Component.literal("Done"),
                                button -> this.onClose()
                        )
                        .bounds(
                                this.width / 2 - 100,
                                this.height - 40,
                                200,
                                20
                        )
                        .build()
        );
    }

    private Component getAllModesButtonText() {

        boolean allEnabled =
                SettingsState.debugEnabled
                        && SettingsState.terraformEnabled
                        && SettingsState.buildEnabled
                        && SettingsState.copyPasteEnabled
                        && SettingsState.lightEnabled
                        && SettingsState.nbtEnabled
                        && SettingsState.utilityEnabled;

        return Component.literal(
                "All Modes: " +
                        (allEnabled ? "ON" : "OFF")
        );
    }

    @Override
    public void render(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {

        this.renderBlurredBackground(partialTick);

        guiGraphics.drawCenteredString(
                this.font,
                this.title,
                this.width / 2,
                40,
                0xFFFFFF
        );

        super.render(
                guiGraphics,
                mouseX,
                mouseY,
                partialTick
        );
    }

    @Override
    public void onClose() {

        if (this.minecraft != null) {
            this.minecraft.setScreen(parent);
        }
    }
}