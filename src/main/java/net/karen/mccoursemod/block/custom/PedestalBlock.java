package net.karen.mccoursemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.karen.mccoursemod.block.entity.PedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class PedestalBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);
    public static final MapCodec<PedestalBlock> CODEC = simpleCodec(PedestalBlock::new);

    public PedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level,
                                           @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /* BLOCK ENTITY */
    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos,
                                      @NotNull BlockState blockState) {
        return new PedestalBlockEntity(blockPos, blockState);
    }

    @Override
    protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack,
                                                   @NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                                   @NotNull Player player, @NotNull InteractionHand hand,
                                                   @NotNull BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
            if (player.isShiftKeyDown() && !level.isClientSide()) {
                player.openMenu(new SimpleMenuProvider(pedestalBlockEntity, Component.literal("Pedestal")), pos);
                return InteractionResult.SUCCESS;
            }
            ItemStack itemStack = pedestalBlockEntity.getItem();
            ItemStacksResourceHandler slot = pedestalBlockEntity.inventory;
            try (Transaction tx = Transaction.openRoot()) {
                if (itemStack.isEmpty() && !stack.isEmpty() &&
                    slot.insert(0, ItemResource.of(stack.copy()), 1, tx) == 1) {
                    tx.commit();
                    stack.shrink(1);
                    level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1F, 2F);
                }
                else if (stack.isEmpty() && !itemStack.isEmpty() &&
                         slot.extract(0, ItemResource.of(itemStack), 1, tx) == 1) {
                    tx.commit();
                    player.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
                    pedestalBlockEntity.clearContents();
                    level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1F, 1F);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}