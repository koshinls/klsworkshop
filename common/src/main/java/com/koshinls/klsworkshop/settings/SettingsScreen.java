package com.koshinls.klsworkshop.settings;

import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import com.koshinls.klsworkshop.settings.screens.DebugSettingsScreen;

public class SettingsScreen extends Screen {

    private final Screen parent;

    public SettingsScreen(Screen parent) {
        super(Component.literal("Creative Wrench Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {

        int buttonWidth = 150;
        int buttonHeight = 20;
        int gap = 10;

        int totalWidth = buttonWidth * 2 + gap;

        int leftX = (this.width - totalWidth) / 2;
        int rightX = leftX + buttonWidth + gap;

        int startY = 55;

        WrenchMode[] modes = WrenchMode.values();

        for (int i = 0; i < modes.length; i++) {

            WrenchMode mode = modes[i];

            int column = i % 2;
            int row = i / 2;

            int x = column == 0 ? leftX : rightX;
            int y = startY + row * (buttonHeight + gap);

            this.addRenderableWidget(
                    Button.builder(
                                    mode.getDisplayName(),
                                    button -> {

                                        if (mode == WrenchMode.DEBUG) {
                                            this.minecraft.setScreen(
                                                    new DebugSettingsScreen(this)
                                            );
                                        }

                                    }
                            )
                            .bounds(
                                    x,
                                    y,
                                    buttonWidth,
                                    buttonHeight
                            )
                            .build()
            );
        }

        this.addRenderableWidget(
                Button.builder(
                                Component.literal("Done"),
                                button -> this.onClose()
                        )
                        .bounds(
                                this.width / 2 - 75,
                                this.height - 35,
                                150,
                                20
                        )
                        .build()
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
                25,
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
