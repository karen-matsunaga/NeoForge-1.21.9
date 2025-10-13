package net.karen.mccoursemod.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public record GemEmpoweringStationRecipeInput(List<ItemStack> inputItems) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int i) { return inputItems.get(i); }

    @Override
    public int size() { return 2; }
}