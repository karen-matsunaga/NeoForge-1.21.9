package net.karen.top.screen.custom;

import net.karen.top.Top;
import net.karen.top.screen.renderer.EnergyDisplayTooltipArea;
import net.karen.top.util.MouseUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GemEmpoweringStationScreen extends AbstractContainerScreen<GemEmpoweringStationMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Top.MOD_ID,
                                                  "textures/gui/gem_empowering_station/gem_empowering_station_gui.png");

    private EnergyDisplayTooltipArea energyInfoArea;

    public GemEmpoweringStationScreen(GemEmpoweringStationMenu menu,
                                      Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    // DEFAULT METHOD - Titles to show on custom block entity GUI
    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
        assignEnergyInfoArea();
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        renderEnergyAreaTooltip(guiGraphics, mouseX, mouseY, x, y);
    }

    // CUSTOM METHOD - Energy tooltip
    private void renderEnergyAreaTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY,
                                         int x, int y) {
        if (isMouseAboveArea(mouseX, mouseY, x, y, 156, 11, 8, 64)) {
            guiGraphics.renderTooltip(this.font, energyInfoArea.getClientTooltip(), mouseX - x, mouseY - y,
                                      DefaultTooltipPositioner.INSTANCE, null);
        }
    }

    // CUSTOM METHOD - Energy amount
    private void assignEnergyInfoArea() {
        energyInfoArea = new EnergyDisplayTooltipArea(((width - imageWidth) / 2) + 156,
                                                      ((height - imageHeight) / 2) + 11,
                                                      menu.blockEntity.getEnergyStorage());
    }

    // DEFAULT METHOD - Render GUI texture
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, imageWidth, imageHeight,
                         256, 256);
        renderProgressArrow(guiGraphics, x, y);
        energyInfoArea.render(guiGraphics);
    }

    // CUSTOM METHOD - Render progress arrow when item transform on other
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 85, y + 30, 176, 0, 8,
                             menu.getScaledProgress(), 256, 256);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    // CUSTOM METHOD - Energy mouse area
    private boolean isMouseAboveArea(int mouseX, int mouseY, int x, int y,
                                     int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, width, height);
    }
}