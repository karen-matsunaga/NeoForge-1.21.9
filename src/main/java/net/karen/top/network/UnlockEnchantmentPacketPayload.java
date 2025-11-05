package net.karen.top.network;

import net.karen.top.Top;
import net.karen.top.component.ModDataComponentTypes;
import net.karen.top.enchantment.ModEnchantments;
import net.karen.top.util.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import static net.karen.top.util.ChatUtils.*;

public record UnlockEnchantmentPacketPayload(boolean locked,
                                             int index) implements CustomPacketPayload {
    // TYPE
    public static final CustomPacketPayload.Type<UnlockEnchantmentPacketPayload> TYPE =
           new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Top.MOD_ID, "unlock_data"));

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return TYPE; }

    // STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, UnlockEnchantmentPacketPayload> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.BOOL, UnlockEnchantmentPacketPayload::locked,
                                 ByteBufCodecs.INT, UnlockEnchantmentPacketPayload::index,
                                 UnlockEnchantmentPacketPayload::new);

    // SERVER PAYLOAD HANDLER
    public static void onUnlockEnchantmentServerPayloadHandler(UnlockEnchantmentPacketPayload payload,
                                                               IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                HolderLookup.RegistryLookup<Enchantment> ench =
                      serverPlayer.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                ItemStack target = serverPlayer.getInventory().getItem(payload.index);
                // Item with UNLOCK enchantment
                if (!target.isEmpty() && Utils.toolEnchant(ench, ModEnchantments.UNLOCK, target) > 0) {
                    target.set(ModDataComponentTypes.UNLOCK, payload.locked);
                    playerDefault(serverPlayer, payload.locked ? "§c\uD83D\uDD12 Item locked!" : "§a\uD83D\uDD13 Item unlocked!");
                }
            }
        })
        .exceptionally(e -> { // Handle exception
            context.disconnect(Component.translatable(top + ".networking.failed", e.getMessage()));
            return null;
        });
    }
}