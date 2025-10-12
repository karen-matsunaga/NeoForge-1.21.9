//package net.karen.mccoursemod.screen.custom;
//
//import net.karen.mccoursemod.MccourseMod;
//import net.karen.mccoursemod.util.EnergyDisplayTooltipArea;
//import net.karen.mccoursemod.util.FluidTankRenderer;
//import net.karen.mccoursemod.util.MouseUtil;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
//import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
//import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
//import net.minecraft.client.renderer.RenderPipelines;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.item.TooltipFlag;
//import net.neoforged.neoforge.fluids.FluidStack;
//import org.jetbrains.annotations.NotNull;
//import java.util.Collections;
//import java.util.List;
//
//public class GemEmpoweringStationScreen extends AbstractContainerScreen<GemEmpoweringStationMenu> {
//    private static final ResourceLocation TEXTURE =
//            ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
//                                                  "textures/gui/gem_empowering_station/gem_empowering_station_gui.png");
//    private EnergyDisplayTooltipArea energyInfoArea;
//    private FluidTankRenderer fluidRenderer;
//
//    public GemEmpoweringStationScreen(GemEmpoweringStationMenu menu, Inventory inventory, Component title) {
//        super(menu, inventory, title);
//    }
//
//    // Titles to show on custom block entity GUI
//    @Override
//    protected void init() {
//        super.init();
//        this.inventoryLabelY = 10000;
//        this.titleLabelY = 10000;
//        assignEnergyInfoArea();
//        assignFluidRenderer();
//    }
//
//    // Fluid renderer texture screen
//    private void assignFluidRenderer() {
//        fluidRenderer = new FluidTankRenderer(64000, true, 16, 39);
//    }
//
//    @Override
//    protected void renderLabels(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
//        int x = (width - imageWidth) / 2;
//        int y = (height - imageHeight) / 2;
//        renderEnergyAreaTooltip(guiGraphics, mouseX, mouseY, x, y);
//        renderFluidTooltipArea(guiGraphics, mouseX, mouseY, x, y, menu.blockEntity.getFluid(),
//                               26, 11, fluidRenderer);
//    }
//
//    private void renderFluidTooltipArea(GuiGraphics guiGraphics, int pMouseX, int pMouseY,
//                                        int x, int y, FluidStack stack, int offsetX, int offsetY,
//                                        FluidTankRenderer renderer) {
//        if (isMouseAboveArea(pMouseX, pMouseY, x, y, offsetX, offsetY, renderer)) {
//            List<ClientTooltipComponent> componentList =
//                Collections.singletonList(ClientTooltipComponent.create(renderer.getTooltip(stack, TooltipFlag.Default.NORMAL)
//                                                                .getFirst().getVisualOrderText()));
//            guiGraphics.renderTooltip(this.font, componentList, pMouseX - x, pMouseY - y,
//                                      DefaultTooltipPositioner.INSTANCE, TEXTURE);
//        }
//    }
//
//    private void renderEnergyAreaTooltip(GuiGraphics guiGraphics, int pMouseX, int pMouseY,
//                                         int x, int y) {
//        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 156, 11, 8, 64)) {
//            guiGraphics.renderTooltip(this.font, energyInfoArea.getClientTooltip(),
//                                      pMouseX - x, pMouseY - y, DefaultTooltipPositioner.INSTANCE, TEXTURE);
//        }
//    }
//
//    private void assignEnergyInfoArea() {
//        energyInfoArea = new EnergyDisplayTooltipArea(((width - imageWidth) / 2) + 156,
//                                                      ((height - imageHeight) / 2) + 11,
//                                                      menu.blockEntity.getEnergyStorage());
//    }
//
//    // Render GUI texture
//    @Override
//    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
//        int x = (width - imageWidth) / 2;
//        int y = (height - imageHeight) / 2;
//
//        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, imageWidth, imageHeight,
//                         256, 256);
//
//        renderProgressArrow(guiGraphics, x, y);
//
//        energyInfoArea.render(guiGraphics);
//        fluidRenderer.render(guiGraphics, x + 26, y + 11, menu.blockEntity.getFluid());
//    }
//
//    // Render progress arrow when item transform on other
//    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
//        if (menu.isCrafting()) {
//            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 85, y + 30, 176, 0, 8,
//                             menu.getScaledProgress(), 256, 256);
//        }
//    }
//
//    @Override
//    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
//        renderBackground(guiGraphics, mouseX, mouseY, delta);
//        super.render(guiGraphics, mouseX, mouseY, delta);
//        renderTooltip(guiGraphics, mouseX, mouseY);
//    }
//
//    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y,
//                                     int offsetX, int offsetY, FluidTankRenderer renderer) {
//        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY,
//                renderer.getWidth(), renderer.getHeight());
//    }
//
//    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y,
//                                     int offsetX, int offsetY, int width, int height) {
//        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
//    }
//}