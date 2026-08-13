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
                                getMasterButtonText(),
                                button -> {

                                    SettingsState.masterEnabled =
                                            !SettingsState.masterEnabled;

                                    button.setMessage(
                                            getMasterButtonText()
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

    private Component getMasterButtonText() {

        return Component.literal(
                "All Modes: " +
                        (SettingsState.masterEnabled
                                ? "ON"
                                : "OFF")
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