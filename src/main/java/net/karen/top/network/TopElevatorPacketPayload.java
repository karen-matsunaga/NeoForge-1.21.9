package net.karen.top.network;

import io.netty.buffer.ByteBuf;
import net.karen.top.Top;
import net.karen.top.block.ModBlocks;
import net.karen.top.block.custom.TopElevatorBlock;
import net.karen.top.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import static net.karen.top.util.ChatUtils.top;

public record TopElevatorPacketPayload(boolean bool) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<TopElevatorPacketPayload> TYPE =
           new CustomPacketPayload.Type<>(Utils.topPath(Top.MOD_ID + "_elevator_data"));

    // Each pair of elements defines the stream codec of the element to encode/decode and the getter for the element to encode
    // 'bool' will be encoded and decoded as a boolean
    // The final parameter takes in the previous parameters in the order they are provided to construct the payload object
    public static final StreamCodec<ByteBuf, TopElevatorPacketPayload> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.BOOL, TopElevatorPacketPayload::bool, TopElevatorPacketPayload::new);

    @Override
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() { return TYPE; }

    // SERVER NETWORK -> Top Elevator block
    public static void onTopElevatorServerPayloadHandler(TopElevatorPacketPayload payload,
                                                         IPayloadContext context) {
        context.enqueueWork(() -> {
                            Player player = context.player();
                            if (player instanceof ServerPlayer serverPlayer) {
                                BlockPos pos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY() - 1, serverPlayer.getZ());
                                if (serverPlayer.level().getBlockState(pos).getBlock() == ModBlocks.TOP_ELEVATOR.get()) {
                                    if (payload.bool()) { TopElevatorBlock.blockUp(serverPlayer); } // Player UP
                                    else { TopElevatorBlock.blockDown(serverPlayer); } // Player DOWN
                                }
                            }
        })
        .exceptionally(e -> { // Handle exception
            context.disconnect(Component.translatable(top + "networking.failed", e.getMessage()));
            return null;
        });
    }
}