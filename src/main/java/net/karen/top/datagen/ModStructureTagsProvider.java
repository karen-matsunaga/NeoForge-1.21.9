package net.karen.top.datagen;

import net.karen.top.Top;
import net.karen.top.util.ModTags;
import net.karen.top.worldgen.structure.ModStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;
import static net.karen.top.util.ChatUtils.*;

public class ModStructureTagsProvider extends StructureTagsProvider {
    public ModStructureTagsProvider(PackOutput output,
                                    CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Top.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ModTags.Structures.TOP_STRUCTURES).addOptional(ModStructures.KAUPEN_HOUSE)
                                                   .addOptional(ModStructures.STORAGE_PLATFORM)
                                                   .replace(false);
    }

    @Override
    public @NotNull String getName() { return splitWord(itemLines(Top.MOD_ID)) + " Structure Tags"; }
}