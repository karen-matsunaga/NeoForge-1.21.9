package net.karen.mccoursemod.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;

public class ModClientTooltipComponent implements ClientTooltipComponent, TooltipComponent {
    private final ResourceLocation texture;
    private final int width, height; // WIDTH and HEIGHT of texture
    private final String text; // TEXT appears next to ICON

    public ModClientTooltipComponent(ResourceLocation texture, int width, int height, String text) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    @Override
    public int getHeight(@NotNull Font font) { return height; }

    @Override
    public int getWidth(Font font) { return width + 4 + font.width(text); }

    @Override
    public void renderText(GuiGraphics guiGraphics, Font font, int x, int y) {
        Matrix3x2fStack poseStack = guiGraphics.pose();
        // Render Image -> Example: Icon 8x8, Icon 9x9, Icon 16x16, etc. (width x height)
        poseStack.pushMatrix();
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0, 0, width, height, width, height); // Render TEXTURE
        poseStack.popMatrix();
        // Render Text -> Example: [ICON] text...
        poseStack.pushMatrix();
        int textX = x + width + 4; // 4px image spacing
        int textY = y + (height - font.lineHeight) / 2; // Center vertically
        guiGraphics.drawString(font, text, textX, textY, ChatUtils.whiteColor, true); // Render TEXT
        poseStack.popMatrix();
    }
}