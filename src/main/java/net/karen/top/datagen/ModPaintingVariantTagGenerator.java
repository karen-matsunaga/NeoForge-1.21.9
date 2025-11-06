package net.karen.top.datagen;

import net.karen.top.Top;
import net.karen.top.painting.ModPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;
import static net.karen.top.util.ChatUtils.*;

public class ModPaintingVariantTagGenerator extends PaintingVariantTagsProvider {
    public ModPaintingVariantTagGenerator(PackOutput output,
                                          CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Top.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(PaintingVariantTags.PLACEABLE).addOptional(ModPaintingVariants.SAW_THEM)
                                          .addOptional(ModPaintingVariants.SHRIMP)
                                          .addOptional(ModPaintingVariants.WORLD);
    }

    @Override
    public @NotNull String getName() { return splitWord(itemLines(Top.MOD_ID)) + " Painting Variant Tags"; }
}