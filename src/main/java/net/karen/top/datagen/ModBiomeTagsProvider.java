package net.karen.top.datagen;

import net.karen.top.Top;
import net.karen.top.util.ModTags;
import net.karen.top.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;
import static net.karen.top.util.ChatUtils.*;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Top.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // KAUPEN HOUSE STRUCTURE
        this.tag(ModTags.Biomes.HAS_KAUPEN_HOUSE).addOptionalTag(BiomeTags.IS_JUNGLE)
                                                 .addOptionalTag(BiomeTags.IS_FOREST)
                                                 .addOptionalTag(BiomeTags.IS_TAIGA)
                                                 .addOptionalTag(ModTags.Biomes.IS_KAUPENDIM)
                                                 .addOptional(Biomes.DESERT)
                                                 .addOptional(Biomes.PLAINS)
                                                 .addOptional(Biomes.SNOWY_PLAINS)
                                                 .addOptional(Biomes.SUNFLOWER_PLAINS)
                                                 .addOptional(Biomes.SAVANNA)
                                                 .addOptional(Biomes.SAVANNA_PLATEAU)
                                                 .addOptional(Biomes.WINDSWEPT_SAVANNA)
                                                 .replace(false);

        // STORAGE PLATFORM JIGSAW STRUCTURE
        this.tag(ModTags.Biomes.HAS_STORAGE_PLATFORM).addOptionalTag(BiomeTags.IS_JUNGLE)
                                                     .addOptionalTag(BiomeTags.IS_FOREST)
                                                     .addOptionalTag(BiomeTags.IS_TAIGA)
                                                     .addOptionalTag(ModTags.Biomes.IS_KAUPENDIM)
                                                     .addOptional(Biomes.DESERT)
                                                     .addOptional(Biomes.PLAINS)
                                                     .addOptional(Biomes.SNOWY_PLAINS)
                                                     .addOptional(Biomes.SUNFLOWER_PLAINS)
                                                     .addOptional(Biomes.SAVANNA)
                                                     .addOptional(Biomes.SAVANNA_PLATEAU)
                                                     .addOptional(Biomes.WINDSWEPT_SAVANNA)
                                                     .replace(false);

        // CUSTOM BIOMES -> KAUPENDIM dimension
        this.tag(ModTags.Biomes.IS_KAUPENDIM).addOptional(ModBiomes.TEST_BIOME)
                                             .addOptional(ModBiomes.TEST_BIOME_2)
                                             .replace(false);
    }

    @Override
    public @NotNull String getName() { return splitWord(itemLines(Top.MOD_ID)) + " Biome Tags"; }
}