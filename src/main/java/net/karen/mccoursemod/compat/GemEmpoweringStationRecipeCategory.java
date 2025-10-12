package net.karen.mccoursemod.compat;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.recipe.GemEmpoweringStationRecipe;
import net.karen.mccoursemod.util.EnergyDisplayTooltipArea;
import net.karen.mccoursemod.util.ModEnergyStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class GemEmpoweringStationRecipeCategory implements IRecipeCategory<GemEmpoweringStationRecipe> {
    public static final ResourceLocation UID =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "gem_empowering");

    public static final ResourceLocation TEXTURE =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "textures/gui/gem_empowering_station_gui.png");

    public static final IRecipeType<GemEmpoweringStationRecipe> GEM_EMPOWERING_TYPE =
           IRecipeType.create(UID, GemEmpoweringStationRecipe.class);

    private final IDrawable background, icon;
    private final IDrawableAnimated arrow;

    public GemEmpoweringStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 0, 0, 176, 83).build(); // Background
        this.icon = helper.createDrawableItemLike(ModBlocks.GEM_EMPOWERING_STATION.get()); // Icon
        this.arrow = helper.createAnimatedDrawable(helper.createDrawable(TEXTURE, 176, 0, 10, 30), 200,
                                                   IDrawableAnimated.StartDirection.TOP, false);
    }

    @Override
    public @NotNull IRecipeType<GemEmpoweringStationRecipe> getRecipeType() { return GEM_EMPOWERING_TYPE; }

    @Override
    public @NotNull Component getTitle() { return Component.literal("Gem Infusing Station"); } // Title appears on screen

    @Override
    public IDrawable getIcon() { return this.icon; } // Box slot appears on screen

    @Override
    public int getHeight() { return background.getHeight(); }

    @Override
    public int getWidth() { return background.getWidth(); }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GemEmpoweringStationRecipe recipe,
                          @NotNull IFocusGroup focuses) {
        // Recipe INPUT slot on screen
        builder.addInputSlot(80, 11).add(recipe.inputItems().getFirst()).setStandardSlotBackground();

        // Recipe ENERGY renderer slot on screen
        builder.addInputSlot(134, 59).add(new ItemStack(ModItems.KOHLRABI.get())).setStandardSlotBackground();

        // Recipe OUTPUT slot on screen
        builder.addOutputSlot(80, 59).add(recipe.output().getItem()).setOutputSlotBackground();
    }

    // Energy Renderer on screen
    @Override
    public void draw(@NotNull GemEmpoweringStationRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
                     @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        float seconds = recipe.getCraftTime(); // Craft Time
        if (seconds > 0) {
            Component info = Component.translatable("gui.jei.category.smelting.time.seconds", seconds);
            guiGraphics.drawString(fontRenderer, info, 120, 45, 0xFF808080, false);
        }
        energyTooltip(energyStorage(recipe)).render(guiGraphics); // Draws the power bar
        arrow.draw(guiGraphics, 85, 30); // Draw animated arrow
    }

    // Energy Renderer on screen
    @Override
    public void getTooltip(@NotNull ITooltipBuilder tooltip, @NotNull GemEmpoweringStationRecipe recipe,
                           @NotNull IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        int x = 156, y = 11, width = 8, height = 64;
        if (mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height) {
            List<Component> energyList = energyTooltip(energyStorage(recipe)).getTooltips();
            for (Component component : energyList) { tooltip.add(component); }
        }
    }

    private ModEnergyStorage energyStorage(GemEmpoweringStationRecipe recipe) {
        ModEnergyStorage energy = new ModEnergyStorage(64000, 200) {
            @Override public void onEnergyChanged() {}
        };
        energy.setEnergy(recipe.getEnergyAmount());
        return energy;
    }

    private EnergyDisplayTooltipArea energyTooltip(ModEnergyStorage stored) {
        return new EnergyDisplayTooltipArea(156, 11, stored);
    }
}