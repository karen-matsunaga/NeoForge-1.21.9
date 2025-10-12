//package net.karen.mccoursemod.recipe;
//
//import net.minecraft.core.NonNullList;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeInput;
//import net.neoforged.neoforge.fluids.FluidStack;
//import org.jetbrains.annotations.NotNull;
//
//public record GemEmpoweringStationRecipeInput(NonNullList<Ingredient> inputItems, ItemStack output,
//                                              int craftTime, int energyAmount,
//                                              FluidStack fluidStack) implements RecipeInput {
//    @Override
//    public @NotNull ItemStack getItem(int i) { return new ItemStack(inputItems.getFirst().getValues().get(0)); }
//
//    @Override
//    public int size() { return 4; }
//}