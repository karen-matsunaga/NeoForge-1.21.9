package net.karen.top.datagen;

import net.karen.top.Top;
import net.karen.top.fluid.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;
import static net.karen.top.util.ChatUtils.*;

public class ModFluidTagGenerator extends FluidTagsProvider {
    public ModFluidTagGenerator(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Top.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(FluidTags.WATER).add(ModFluids.SOURCE_SOAP_WATER.get(), ModFluids.FLOWING_SOAP_WATER.get());
    }

    @Override
    public @NotNull String getName() { return splitWord(itemLines(Top.MOD_ID)) + " Fluid Tags"; }
}