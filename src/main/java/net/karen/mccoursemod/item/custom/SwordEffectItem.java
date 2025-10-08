package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.entity.custom.MagicProjectileEntity;
import net.karen.mccoursemod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import static net.karen.mccoursemod.util.ChatUtils.componentTranslatableIntColor;
import static net.karen.mccoursemod.util.Utils.*;

public class SwordEffectItem extends Item {
    private final int color;

    public SwordEffectItem(Item.Properties properties, ToolMaterial material,
                           float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        super(properties.sword(material, attackDamage, attackSpeed).fireResistant().repairable(repair));
        this.color = color;
    }

    // DEFAULT METHOD - Sword Effect only LEFT click on mouse button
    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        if (entity instanceof LivingEntity livingEntity) { // Every time the player hit on entity to create slowing effect
            livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 400), player);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    // DEFAULT METHOD - Sword Effect only RIGHT click on mouse button
    @Override
    public @NotNull InteractionResult use(@NotNull Level level,
                                          @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand); // Player has RADIATION STAFF item
        neutralSound(level, player, ModSounds.METAL_DETECTOR_FOUND_ORE.get(), 1.5F, 1F); // Sound of Magic Projectile
        player.getCooldowns().addCooldown(new ItemStack(this), 20);
        if (!level.isClientSide()) { // CLIENT and SERVER created Magic Projectile
            MagicProjectileEntity magicProjectile = new MagicProjectileEntity(level, player); // Added velocity and inaccuracy
            magicProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.25F);
            level.addFreshEntity(magicProjectile); // Added Magic Projectile on world
        }
        player.awardStat(Stats.ITEM_USED.get(this)); // Item hurt
        if (!player.getAbilities().instabuild) {
            itemstack.hurtAndBreak(1, player, player.getUsedItemHand());
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatableIntColor(this.getDescriptionId(), color);
    }

    // CUSTOM METHOD - TELEPORT effect
    public static void createTeleportEffect(PlayerInteractEvent event,
                                            boolean bool) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        Level level = event.getLevel();
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            if (player instanceof ServerPlayer serverPlayer) { // Teleport when using item (like tools, etc.)
                // Check if holding Alexandrite Sword item or has Sword Effect Item class
                if (!stack.isEmpty() && bool) {
                    /* 1. Get the direction the player is looking;
                       2. It starts from the eyes;
                       3. Block render distance. How many blocks ahead to ray trace (reach distance). */
                    Vec3 look = serverPlayer.getLookAngle();
                    Vec3 start = serverPlayer.getEyePosition();
                    Vec3 end = start.add(look.scale(5.0));
                    // Ray trace until it hits a block
                    BlockHitResult hitResult = hitBlock(serverLevel, start, end, serverPlayer);
                    if (hitResult.getType() == HitResult.Type.BLOCK) {
                        BlockPos blockPos = hitResult.getBlockPos(); // Teleports to the top of the block hit (+1 height)
                        double x = blockPos.getX() + 0.5;
                        double y = blockPos.getY() + 1.0;
                        double z = blockPos.getZ() + 0.5;
                        float xRot = serverPlayer.getXRot();
                        float yRot = serverPlayer.getYRot();
                        serverPlayer.teleportTo(serverLevel, x, y, z, Set.of(), yRot, xRot, true);
                    }
                }
            }
        }
    }
}