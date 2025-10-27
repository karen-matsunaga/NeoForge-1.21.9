package net.karen.mccoursemod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record Coordinates(BlockPos blockPos) {
    // CODEC
    public static Codec<Coordinates> CODEC =
           RecordCodecBuilder.create(instance ->
                                     instance.group(BlockPos.CODEC.fieldOf("blockPos").forGetter(Coordinates::blockPos))
                                             .apply(instance, Coordinates::new));

    // STREAM CODEC
    public static StreamCodec<RegistryFriendlyByteBuf, Coordinates> STREAM_CODEC =
           StreamCodec.composite(BlockPos.STREAM_CODEC, Coordinates::blockPos, Coordinates::new);

    public String description() {
        return "Last Block changed at [X: " + this.blockPos.getX() +
               ", Y: " +  this.blockPos.getY() + ", Z: " + this.blockPos.getZ() + "]";
    }
}