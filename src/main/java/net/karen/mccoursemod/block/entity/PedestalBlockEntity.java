package net.karen.mccoursemod.block.entity;

import net.karen.mccoursemod.screen.custom.PedestalMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class PedestalBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(1) {
        @Override
        protected void onContentsChanged(int index, @NotNull ItemStack previousContents) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public int size() { return 1; }

        @Override
        protected int getCapacity(int index, @NotNull ItemResource resource) { return 1; }
    };
    private float rotation;

    // CUSTOM METHOD - Output SLOT
    public ItemStacksResourceHandler getInventory() {
        return this.inventory;
    }

    // CUSTOM METHOD - Item Stack on OUTPUT SLOT
    public ItemStack getItem() {
        ItemStack itemStack = getInventory().getResource(0).toStack();
        if (!itemStack.isEmpty()) { return itemStack; }
        return ItemStack.EMPTY;
    }

    public PedestalBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.PEDESTAL_BE.get(), pos, blockState);
    }

    // CUSTOM METHOD - Render item rotation
    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) { rotation = 0; }
        return rotation;
    }

    // CUSTOM METHOD - Remove item
    public void clearContents() {
        inventory.set(0, ItemResource.of(ItemStack.EMPTY), 0);
    }

    // CUSTOM METHOD - Item drop
    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) { inv.setItem(i, inventory.getResource(i).toStack()); }
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
        inventory.serialize(output);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        inventory.deserialize(input);
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