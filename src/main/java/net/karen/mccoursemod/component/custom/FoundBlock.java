package net.karen.mccoursemod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Map;
import java.util.Objects;
import static net.karen.mccoursemod.item.custom.MetalDetectorItem.oreColors;
import static net.karen.mccoursemod.util.ChatUtils.*;

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

    // CUSTOM METHOD - DATA TABLET found an ore + METAL DETECTOR ore block color
    public Component blockPosition() {
        Block foundBlock = block.getBlock();
        String blockId = foundBlock.getName().getString();
        int x = position.getX(), y = position.getY(), z = position.getZ();
        ChatFormatting color = white; // DEFAULT COLOR
        // KEY -> Ores || VALUE -> Ore colors message
        for (Map.Entry<TagKey<Block>, ChatFormatting> entry : oreColors.entrySet()) {
            TagKey<Block> ores = entry.getKey(); // Ore blocks
            ChatFormatting oreColor = entry.getValue(); // Ore colors message
            if (block.is(ores)) {
                color = oreColor;
                break;
            }
        }
        return Component.literal("Ore Found: ").withStyle(gray).append(componentLiteral(blockId, color))
                        .append(componentLiteral(" at [X: ", gray)).append(componentLiteral(String.valueOf(x), color))
                        .append(componentLiteral(", Y: ", gray)).append(componentLiteral(String.valueOf(y), color))
                        .append(componentLiteral(", Z: ", gray)).append(componentLiteral(String.valueOf(z), color))
                        .append(componentLiteral("]", gray));
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