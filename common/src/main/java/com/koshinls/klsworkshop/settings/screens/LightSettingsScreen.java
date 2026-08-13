package com.koshinls.klsworkshop.settings.screens;

import com.koshinls.klsworkshop.settings.SettingsState;
import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class LightSettingsScreen extends Screen {

    private final Screen parent;

    public LightSettingsScreen(Screen parent) {
        super(Component.literal("Light Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {

        this.addRenderableWidget(
                Button.builder(
                                getButtonText(),
                                button -> {

                                    boolean newState =
                                            !SettingsState.getStoredEnabled(
                                                    WrenchMode.LIGHT
                                            );

                                    SettingsState.setStoredEnabled(
                                            WrenchMode.LIGHT,
                                            newState
                                    );

                                    button.setMessage(
                                            getButtonText()
                                    );
                                }
                        )
                        .bounds(
                                this.width / 2 - 100,
                                70,
                                200,
                                20
                        )
                        .build()
        );

        this.addRenderableWidget(
                new LightLevelSlider(
                        this.width / 2 - 100,
                        105,
                        200,
                        20
                )
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

    private Component getButtonText() {

        if (!SettingsState.masterEnabled) {
            return Component.literal(
                    "Light Mode: OFF (Master OFF)"
            );
        }

        return Component.literal(
                "Light Mode: " +
                        (
                                SettingsState.getStoredEnabled(
                                        WrenchMode.LIGHT
                                )
                                        ? "ON"
                                        : "OFF"
                        )
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

    private static class LightLevelSlider
            extends AbstractSliderButton {

        public LightLevelSlider(
                int x,
                int y,
                int width,
                int height
        ) {
            super(
                    x,
                    y,
                    width,
                    height,
                    Component.empty(),
                    SettingsState.lightLevel / 15.0
            );

            updateMessage();
        }

        @Override
        protected void updateMessage() {

            setMessage(
                    Component.literal(
                            "Light Level: " +
                                    SettingsState.lightLevel +
                                    " / 15"
                    )
            );
        }

        @Override
        protected void applyValue() {

            SettingsState.lightLevel =
                    (int) Math.round(
                            this.value * 15.0
                    );

            updateMessage();
        }
    }
}