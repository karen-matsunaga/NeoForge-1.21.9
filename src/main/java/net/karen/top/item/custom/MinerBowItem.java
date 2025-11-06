package net.karen.top.item.custom;

import net.karen.top.entity.custom.MinerBowArrowEntity;
import net.karen.top.util.ChatUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MinerBowItem extends BowItem {
    private final int radius;
    private final int depth;

    public MinerBowItem(Properties properties, int radius, int depth) {
        super(properties);
        this.radius = radius;
        this.depth = depth;
    }

    // CUSTOM METHOD - RADIUS
    public int getRadius() { return radius; }

    // CUSTOM METHOD - DEPTH
    public int getDepth() { return depth; }

    @Override
    public boolean releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int i) {
        if (!level.isClientSide() && entity instanceof Player player) {
            MinerBowArrowEntity arrow = new MinerBowArrowEntity(player, level);
            arrow.setPos(player.getX(), player.getEyeY(), player.getZ());
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
            level.addFreshEntity(arrow);
            stack.hurtAndBreak(1, player, player.getUsedItemHand());
        }
        return false;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) { return ChatUtils.rgbItemName(stack); }

    // CUSTOM METHOD - MINER BOW description
    public String minerBowDescription() {
        int radius = getRadius() * 2 + 1;
        int depth = getDepth();
        return "Break blocks: " + radius + " x " + radius + " x " + depth;
    }
}