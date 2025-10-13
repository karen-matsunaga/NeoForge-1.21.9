package net.karen.mccoursemod.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GemEmpoweringRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredient;
    private final ItemStack result;
    private final int craftTime;
    private final int energyAmount;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public GemEmpoweringRecipeBuilder(List<Ingredient> ingredient, ItemStack result,
                                      int craftTime, int energyAmount) {
        this.ingredient = ingredient;
        this.result = result;
        this.craftTime = craftTime;
        this.energyAmount = energyAmount;
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String pGroupName) { return this; }

    @Override
    public @NotNull Item getResult() { return this.result.getItem(); }

    // Save all Gem Empowering Station recipe custom recipes
    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceKey<Recipe<?>> resourceKey) {
        Advancement.Builder advancement =
            recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
                        .rewards(AdvancementRewards.Builder.recipe(resourceKey))
                        .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);
        GemEmpoweringStationRecipe recipe =
           new GemEmpoweringStationRecipe(this.ingredient, this.result, this.craftTime, this.energyAmount);
        recipeOutput.accept(resourceKey, recipe, advancement.build(resourceKey.location().withPrefix("recipes/")));
    }
}