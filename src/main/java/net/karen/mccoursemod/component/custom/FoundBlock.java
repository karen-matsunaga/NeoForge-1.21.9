package net.karen.mccoursemod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Objects;

public record FoundBlock(BlockState block, BlockPos position,
                         ResourceKey<Level> dimension, ResourceKey<Biome> biome) {
    public static final Codec<FoundBlock> CODEC =
           RecordCodecBuilder.create(instance ->
                                    // FOUND BLOCK
                     instance.group(BlockState.CODEC.fieldOf("block").forGetter(FoundBlock::block),
                                    // BLOCK POSITION
                                    BlockPos.CODEC.fieldOf("position").forGetter(FoundBlock::position),
                                    // DIMENSION
                                    ResourceKey.codec(Registries.DIMENSION).fieldOf("dimension")
                                                                           .forGetter(FoundBlock::dimension),
                                    // BIOME
                                    ResourceKey.codec(Registries.BIOME).fieldOf("biome")
                                                                       .forGetter(FoundBlock::biome))
                             .apply(instance, FoundBlock::new));

    // CUSTOM METHOD - METAL DETECTOR found an ore
    public String getOutputString() {
        String blockId = block.getBlock().getName().getString();
        int x = position.getX(), y = position.getY(), z = position.getZ();
        return "Valuable Found: " + blockId + " at [X: " + x + ", Y: " + y + ", Z: " + z + "] ";
    }

    // CUSTOM METHOD - Block HASH is equals with Position HASH
    @Override
    public int hashCode() {
        return Objects.hash(this.block, this.position, this.dimension, this.biome);
    }

    // CUSTOM METHOD - Block detected is equals with Position founded
    @Override
    public boolean equals(Object object) {
        if (object == this) { return true; }
        else {
            return object instanceof FoundBlock(BlockState foundedBlock, BlockPos foundedPosition,
                                                ResourceKey<Level> foundedDimension, ResourceKey<Biome> foundedBiome) &&
                   this.block == foundedBlock && this.position == foundedPosition &&
                   this.dimension == foundedDimension && this.biome == foundedBiome;
        }
    }
}