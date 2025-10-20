package net.karen.mccoursemod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.function.Function;

public record GemEmpoweringStationRecipe(List<Ingredient> inputItems,
                                         ItemStack output, int craftTime, int energyAmount)
       implements Recipe<GemEmpoweringStationRecipeInput> {

    @Override
    public boolean matches(@NotNull GemEmpoweringStationRecipeInput input, Level level) {
        if (level.isClientSide()) { return false; }
        return inputItems.getFirst().test(input.getItem(0)); // Verify if the item is equals on input slot
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull GemEmpoweringStationRecipeInput input,
                                       HolderLookup.@NotNull Provider provider) {
        return output.copy();
    }

    @Override
    public @NotNull RecipeSerializer<? extends Recipe<GemEmpoweringStationRecipeInput>> getSerializer() {
        return ModRecipes.GEM_EMPOWERING_STATION_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<? extends Recipe<GemEmpoweringStationRecipeInput>> getType() {
        return ModRecipes.GEM_EMPOWERING_STATION_TYPE.get();
    }

    public List<Ingredient> getInputItems() { return this.inputItems; }

    public int getCraftTime() { return craftTime; } // Craft Time

    public int getEnergyAmount() { return energyAmount; } // Energy Amount

    @Override
    public @NotNull PlacementInfo placementInfo() { return PlacementInfo.create(inputItems); }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() { return RecipeBookCategories.CRAFTING_MISC; }

    // Create JSON custom recipe files
    public static class Serializer implements RecipeSerializer<GemEmpoweringStationRecipe> {
        public static final MapCodec<GemEmpoweringStationRecipe> CODEC =
               RecordCodecBuilder.mapCodec(inst -> inst.group(
                                           Ingredient.CODEC.listOf().fieldOf("ingredient")
                                                     .forGetter(GemEmpoweringStationRecipe::inputItems),
                                           ItemStack.CODEC.fieldOf("output")
                                                    .forGetter(GemEmpoweringStationRecipe::output),
                                           Codec.INT.fieldOf("craftTime")
                                                .forGetter(GemEmpoweringStationRecipe::craftTime),
                                           Codec.INT.fieldOf("energyAmount")
                                                .forGetter(GemEmpoweringStationRecipe::energyAmount))
                                 .apply(inst, GemEmpoweringStationRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, GemEmpoweringStationRecipe> STREAM_CODEC =
               StreamCodec.composite(Ingredient.CONTENTS_STREAM_CODEC
                                               .apply(ByteBufCodecs.list())
                                               .map(List::copyOf, Function.identity()),
                                     GemEmpoweringStationRecipe::inputItems,
                                     ItemStack.STREAM_CODEC, GemEmpoweringStationRecipe::output,
                                     ByteBufCodecs.VAR_INT, GemEmpoweringStationRecipe::craftTime,
                                     ByteBufCodecs.VAR_INT, GemEmpoweringStationRecipe::energyAmount,
                                     GemEmpoweringStationRecipe::new);

        @Override
        public @NotNull MapCodec<GemEmpoweringStationRecipe> codec() { return CODEC; }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, GemEmpoweringStationRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}