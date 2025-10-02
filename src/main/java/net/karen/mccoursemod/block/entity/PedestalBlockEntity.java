package net.karen.mccoursemod.block.entity;

import net.karen.mccoursemod.screen.custom.PedestalMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class PedestalBlockEntity extends BlockEntity implements MenuProvider {
    public final Container inventory = new Container() {
        @Override
        public int getContainerSize() { return 1; }

        @Override
        public boolean isEmpty() { return false; }

        @Override
        public ItemStack getItem(int i) { return null; }

        @Override
        public ItemStack removeItem(int i, int i1) { return null; }

        @Override
        public ItemStack removeItemNoUpdate(int i) { return null; }

        @Override
        public void setItem(int i, @NotNull ItemStack itemStack) {}

        @Override
        public void setChanged() {
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean stillValid(@NotNull Player player) { return false; }

        @Override
        public void clearContent() { inventory.setItem(0, ItemStack.EMPTY); }
    };

    private float rotation;

    public PedestalBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.PEDESTAL_BE.get(), pos, blockState);
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) { rotation = 0; }
        return rotation;
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getContainerSize());
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inv.setItem(i, inventory.getItem(i));
        }
        if (this.level != null) { Containers.dropContents(this.level, this.worldPosition, inv); }
    }

    @Override
    public void preRemoveSideEffects(@NotNull BlockPos pos, @NotNull BlockState state) {
        drops();
        super.preRemoveSideEffects(pos, state);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);
        //inventory.serialize(output);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        //inventory.deserialize(input);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Pedestal");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i,
                                            @NotNull Inventory inventory, @NotNull Player player) {
        return new PedestalMenu(i, inventory, this);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return saveWithoutMetadata(registries);
    }
}