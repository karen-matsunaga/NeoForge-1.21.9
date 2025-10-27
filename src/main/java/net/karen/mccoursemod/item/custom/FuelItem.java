package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class FuelItem extends Item {
    private final int burnTime;
    private final int color;

    public int getBurnTime() { return burnTime; }

    public FuelItem(Properties properties, int burnTime, int color) {
        super(properties);
        this.burnTime = burnTime;
        this.color = color;
    }

    @Override
    public int getBurnTime(@NotNull ItemStack stack, @Nullable RecipeType<?> recipeType,
                           @NotNull FuelValues fuelValues) {
        return this.burnTime;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return ChatUtils.componentTranslatableIntColor(this.getDescriptionId(), color);
    }
}