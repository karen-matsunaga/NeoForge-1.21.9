package net.karen.top.network;

import net.karen.top.component.ModDataComponentTypes;
import net.karen.top.enchantment.custom.DashEnchantmentEffect;
import net.karen.top.util.Utils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import static net.karen.top.util.ChatUtils.*;

public record DashEnchantmentPacketPayload(ItemStack target) implements CustomPacketPayload {
    // TYPE
    public static final CustomPacketPayload.Type<DashEnchantmentPacketPayload> TYPE =
           new CustomPacketPayload.Type<>(Utils.topPath("dash_data"));

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return TYPE; }

    // STREAM CODEC
    public static final StreamCodec<RegistryFriendlyByteBuf, DashEnchantmentPacketPayload> STREAM_CODEC =
           StreamCodec.composite(ItemStack.STREAM_CODEC, DashEnchantmentPacketPayload::target,
                                 DashEnchantmentPacketPayload::new);

    // Credit by a1qs - https://github.com/a1qs/Dash-Amulet/blob/master/LICENSE
    // https://github.com/a1qs/Dash-Amulet/blob/master/src/main/java/io/github/a1qs/network/C2SDashCooldownPayload.java
    // SERVER PAYLOAD HANDLER
    public static void onDashEnchantmentServerPayloadHandler(DashEnchantmentPacketPayload payload,
                                                             IPayloadContext context) {
        context.enqueueWork(() -> {
                            Player player = context.player();
                            if (player instanceof ServerPlayer serverPlayer) {
                                ServerLevel serverLevel = serverPlayer.level();
                                ItemStack target = payload.target();
                                // DASH enchantment
                                EnchantmentHelper.runIterationOnItem(target, (holder, _) -> {
                                    DashEnchantmentEffect effect =
                                        holder.value().effects().get(ModDataComponentTypes.DASH_ENCHANTMENT_EFFECT.get());
                                    if (effect != null) {
                                        RandomSource random = serverLevel.getRandom();
                                        player.getCooldowns().addCooldown(target, effect.cooldown());
                                        double trailDistance = 0.8;
                                        Vec3 look = player.getLookAngle();
                                        for (int i = 0; i < 20; i++) {
                                            double angle = random.nextDouble() * Math.PI * 2;
                                            double radius = random.nextDouble() * 2;
                                            double spawnX = player.getX() - look.x * trailDistance + Math.cos(angle) * radius;
                                            double spawnY = player.getY() + 0.1 + (random.nextDouble() - 0.5) * 0.2;
                                            double spawnZ = player.getZ() - look.z * trailDistance + Math.sin(angle) * radius;
                                              // PARTICLE
                                            serverLevel.sendParticles(ParticleTypes.SONIC_BOOM, spawnX, spawnY, spawnZ, 1,
                                                                      -look.x * 0.05 + (-0.5) * 0.2, 0D,
                                                                      -look.z * 0.05 + ( - 0.5) * 0.2, 0);
                                        }
                                        // SOUND
                                        serverLevel.playSound(null, player.blockPosition(),
                                                              SoundEvents.WARDEN_SONIC_BOOM,
                                                              SoundSource.PLAYERS, 0.8F, 0.8F);
                                    }
                                });
                            }
        })
        .exceptionally(e -> { // Handle exception
                         context.disconnect(Component.translatable(top + ".networking.failed", e.getMessage()));
                         return null;
                       });
    }
}