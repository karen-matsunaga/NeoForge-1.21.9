package net.karen.top.network;

import io.netty.buffer.ByteBuf;
import net.karen.top.component.ModDataComponentTypes;
import net.karen.top.item.custom.SpecialBottleItem;
import net.karen.top.util.Utils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import java.util.function.IntFunction;
import static net.karen.top.util.ChatUtils.*;

public record SpecialBottlePacketPayload(SpecialBottleEnum actionItem, int amount)
       implements CustomPacketPayload {

    public enum SpecialBottleEnum implements StringRepresentable {
        STORED("Stored"),
        RESTORED("Restored");

        // Special Bottle Enum -> ID
        public static final IntFunction<SpecialBottleEnum> BY_ID =
               ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        // Special Bottle Enum -> STREAM CODEC
        public static final StreamCodec<ByteBuf, SpecialBottleEnum> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Enum::ordinal);

        private final String name;

        SpecialBottleEnum(String name) { this.name = name; }

        @Override
        public @NotNull String getSerializedName() { return this.name; }
    }

    // Special Bottle PACKET PAYLOAD -> TYPE
    public static final CustomPacketPayload.Type<SpecialBottlePacketPayload> TYPE =
           new CustomPacketPayload.Type<>(Utils.topPath("special_bottle_data"));

    // Special Bottle PACKET PAYLOAD -> STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, SpecialBottlePacketPayload> STREAM_CODEC =
           StreamCodec.composite(SpecialBottleEnum.STREAM_CODEC, SpecialBottlePacketPayload::actionItem,
                                 ByteBufCodecs.INT, SpecialBottlePacketPayload::amount,
                                 SpecialBottlePacketPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return TYPE; }

    // SERVER NETWORK -> Special Bottle item
    public static void onSpecialBottleServerPayloadHandler(SpecialBottlePacketPayload payload,
                                                           IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                ItemStack stack = serverPlayer.getMainHandItem(); // Has Special Bottle item on main hand
                if (!(stack.getItem() instanceof SpecialBottleItem self)) { return; }
                // Data Component to store and to restore XP, Store Levels on Special Bottle, Player XP
                Integer storedLevels = stack.get(ModDataComponentTypes.STORED_LEVELS);
                if (storedLevels != null && storedLevels >= 0) { // STORED
                    int maxLevels = self.storeXp,
                        availableLevels = serverPlayer.experienceLevel;
                    if (serverPlayer.getCooldowns().isOnCooldown(stack)) { // Check if it is already on cooldown
                        player(serverPlayer, "Wait before using again!", yellow);
                        return;
                    }
                    // Special Bottle Enum -> STORED
                    if (payload.actionItem == SpecialBottleEnum.STORED) {
                        if (availableLevels <= 0) { // Player store XP only has 1+ levels
                            player(serverPlayer, "You have no XP to store!", red);
                            return;
                        }
                        int canStore = Math.min(payload.amount(), Math.min(maxLevels - storedLevels, availableLevels));
                        if (canStore > 0) {
                            stack.set(ModDataComponentTypes.STORED_LEVELS, storedLevels + canStore);
                            serverPlayer.giveExperienceLevels(-canStore);
                            player(serverPlayer, "Stored " + canStore + " levels!", green);
                        }
                        else { player(serverPlayer, "XP full or insufficient!", red); }
                    }
                    // Special Bottle Enum -> RESTORED
                    if (payload.actionItem == SpecialBottleEnum.RESTORED) {
                        int canRestore = Math.min(payload.amount(), storedLevels);
                        if (canRestore > 0) {
                            stack.set(ModDataComponentTypes.STORED_LEVELS, storedLevels - canRestore);
                            serverPlayer.giveExperienceLevels(canRestore);
                            player(serverPlayer, "Restored " + canRestore + " levels!", green);
                        }
                        else { player(serverPlayer, "XP restored!", red); }
                    }
                    serverPlayer.getCooldowns().addCooldown(stack, 20); // Applies 1 second cooldown (20 ticks)
                }
            }
        })
        .exceptionally(e -> { // Handle exception
                          context.disconnect(Component.translatable(top + "networking.failed", e.getMessage()));
                          return null;
                       });
    }
}