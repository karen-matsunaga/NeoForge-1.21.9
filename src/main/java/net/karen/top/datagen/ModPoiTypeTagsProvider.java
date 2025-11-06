package net.karen.top.datagen;

import net.karen.top.Top;
import net.karen.top.villager.ModVillagers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;
import static net.karen.top.util.ChatUtils.*;

public class ModPoiTypeTagsProvider extends PoiTypeTagsProvider {
    public ModPoiTypeTagsProvider(PackOutput output,
                                  CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Top.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        ResourceKey<PoiType> kaupenKey = ModVillagers.KAUPEN_POI.getKey(); // KAUPEN POI
        ResourceKey<PoiType> soundKey = ModVillagers.SOUND_POI.getKey(); // SOUND POI
        if (kaupenKey != null && soundKey != null) {
            this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).addOptional(kaupenKey).addOptional(soundKey);
        }
    }

    @Override
    public @NotNull String getName() { return splitWord(itemLines(Top.MOD_ID)) + " Poi Tags"; }
}