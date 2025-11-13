package net.karen.top.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.karen.top.component.ModDataComponentTypes;
import net.karen.top.network.DashEnchantmentPacketPayload;
import net.karen.top.util.ModKeyMapping;
import net.karen.top.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public record DashEnchantmentEffect(int boost, int cooldown) {
    // CODEC
    public static final Codec<DashEnchantmentEffect> CODEC =
           RecordCodecBuilder.create(instance ->
                                     instance.group(Codec.INT.fieldOf("boost").forGetter(DashEnchantmentEffect::boost),
                                                    Codec.INT.fieldOf("cooldown").forGetter(DashEnchantmentEffect::cooldown))
                                             .apply(instance, DashEnchantmentEffect::new));

    // STREAM CODEC
    public static final StreamCodec<ByteBuf, DashEnchantmentEffect> STREAM_CODEC =
           StreamCodec.composite(ByteBufCodecs.INT, DashEnchantmentEffect::boost, ByteBufCodecs.INT, DashEnchantmentEffect::cooldown,
                                 DashEnchantmentEffect::new);

    // Credit by a1qs - https://github.com/a1qs/Dash-Amulet/blob/master/LICENSE
    // https://github.com/a1qs/Dash-Amulet/blob/master/src/main/java/io/github/a1qs/event/OnKeyPress.java
    // CUSTOM METHOD - DASH enchantment event [ClientTickEvent Post Event]
    public static void onKeyPressEvent() {
        if (ModKeyMapping.DASH_KEY.get().consumeClick()) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            Level level = mc.level;
            if (player == null || level == null) { return; }
            ItemStack item = Utils.has(player, EquipmentSlot.FEET);
            EnchantmentHelper.runIterationOnItem(item, (holder, _) -> {
                DashEnchantmentEffect effect =
                    holder.value().effects().get(ModDataComponentTypes.DASH_ENCHANTMENT_EFFECT.get());
                if (effect != null) {
                    ClientPacketDistributor.sendToServer(new DashEnchantmentPacketPayload(item));
                    Vec3 lookVector = player.getLookAngle();
                    Vec3 dashVector = new Vec3(lookVector.x(), lookVector.y(), lookVector.z());
                    float initialYaw = (float) Math.atan2(dashVector.z(), dashVector.x());
                    dashVector = dashVector.yRot(initialYaw);
                    double dashPitch = Math.toDegrees(Math.asin(dashVector.y() / dashVector.length()));
                    if (dashPitch + 10 > 90) {
                        dashVector = new Vec3(0, 1, 0);
                        dashPitch = 90;
                    }
                    else {
                        dashVector = dashVector.zRot((float) Math.toRadians(-10));
                        dashVector = dashVector.yRot(-initialYaw);
                        dashVector = dashVector.normalize();
                    }
                    double coeff = 1.6 - (0.6 + (0.4) * ((Math.abs(dashPitch)) / 90));
                    dashVector = dashVector.scale(((10 + effect.boost()) * 0.15F) * coeff);
                    player.push(dashVector.x(), dashVector.y(), dashVector.z());
                    player.setDeltaMovement(dashVector);
                    // SOUND
                    player.level().playLocalSound(player, SoundEvents.WARDEN_SONIC_BOOM,
                                                  SoundSource.PLAYERS, 0.8F, 0.8F);
                }
            });
        }
    }
}