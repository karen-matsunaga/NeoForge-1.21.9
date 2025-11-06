package net.karen.top.item.custom;

import net.karen.top.component.custom.FoundBlock;
import net.karen.top.component.ModDataComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import static net.karen.top.util.ChatUtils.*;

public class DataTabletItem extends Item {
    public DataTabletItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack playerItem = player.getItemInHand(hand);
        FoundBlock foundBlockData = playerItem.get(ModDataComponentTypes.FOUND_BLOCK.get());
        if (foundBlockData != null) { playerItem.set(ModDataComponentTypes.FOUND_BLOCK.get(), null); }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        FoundBlock foundBlockData = stack.get(ModDataComponentTypes.FOUND_BLOCK.get());
        return foundBlockData != null;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatable(this.getDescriptionId(), darkGray);
    }
}