package net.karen.mccoursemod.block.entity;

import net.karen.mccoursemod.recipe.GrowthChamberRecipe;
import net.karen.mccoursemod.recipe.GrowthChamberRecipeInput;
import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.custom.GrowthChamberMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.Optional;

public class GrowthChamberBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStacksResourceHandler itemHandler =
           new ItemStacksResourceHandler(2) {
        @Override protected void onContentsChanged(int index,
                                                   @NotNull ItemStack previousContents) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    // CUSTOM METHOD - Input SLOT
    public static int getInputSlot() {
        return INPUT_SLOT;
    }

    // CUSTOM METHOD - Output SLOT
    public static int getOutputSlot() {
        return OUTPUT_SLOT;
    }

    // CUSTOM METHOD - Input + Output SLOTS
    public ItemStacksResourceHandler getItemHandler() {
        return itemHandler;
    }

    public GrowthChamberBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.GROWTH_CHAMBER_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> GrowthChamberBlockEntity.this.progress;
                    case 1 -> GrowthChamberBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: GrowthChamberBlockEntity.this.progress = value;
                    case 1: GrowthChamberBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() { return 2; }
        };
    }

    @Override public @NotNull Component getDisplayName() {
        return Component.translatable("block.mccoursemod.growth_chamber");
    }

    @Nullable @Override public AbstractContainerMenu createMenu(int i,
                                                                @NotNull Inventory inventory,
                                                                @NotNull Player player) {
        return new GrowthChamberMenu(i, inventory, this, this.data);
    }

    // CUSTOM METHOD - Item drops
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.size());
        for (int i = 0; i < itemHandler.size(); i++) {
            inventory.setItem(i, itemHandler.getResource(i).toStack(itemHandler.getAmountAsInt(i)));
        }
        if (this.level != null) { Containers.dropContents(this.level, this.worldPosition, inventory); }
    }

    @Override public void preRemoveSideEffects(@NotNull BlockPos pos,
                                               @NotNull BlockState state) {
        drops();
        super.preRemoveSideEffects(pos, state);
    }

    @Override protected void saveAdditional(@NotNull ValueOutput output) {
        output.store(new CompoundTag());
        itemHandler.serialize(output);
        output.putInt("growth_chamber.progress", progress);
        output.putInt("growth_chamber.max_progress", maxProgress);
        super.saveAdditional(output);
    }

    @Override protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        itemHandler.deserialize(input);
        progress = input.getIntOr("growth_chamber.progress", 0);
        maxProgress = input.getIntOr("growth_chamber.max_progress", 0);
    }

    // CUSTOM METHOD - Item progress
    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);
            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        }
        else { resetProgress(); }
    }

    // CUSTOM METHOD - Item output recipe
    private void craftItem() {
        Optional<RecipeHolder<GrowthChamberRecipe>> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            ItemStack output = recipe.get().value().output();
            Transaction tx = Transaction.open(null);
            itemHandler.extract(itemHandler.getResource(getInputSlot()), 1, tx);
            tx.commit();
            itemHandler.set(getOutputSlot(), ItemResource.of(output.getItem()),
                            itemHandler.getAmountAsInt(getOutputSlot()) + output.getCount());
        }
    }

    // CUSTOM METHOD - Draw progress
    private void resetProgress() {
        progress = 0;
        maxProgress = 72;
    }

    private boolean hasCraftingFinished() { return this.progress >= this.maxProgress; }

    private void increaseCraftingProgress() { progress++; }

    // CUSTOM METHOD - Find Growth Chamber recipes
    private boolean hasRecipe() {
        Optional<RecipeHolder<GrowthChamberRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) { return false; }
        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<GrowthChamberRecipe>> getCurrentRecipe() {
        assert this.level != null;
        return ((ServerLevel) this.level).recipeAccess()
                                         .getRecipeFor(ModRecipes.GROWTH_CHAMBER_TYPE.get(),
                                                       new GrowthChamberRecipeInput(itemHandler.getResource(getInputSlot())
                                                                                               .toStack()),
                                                                                    level);
    }

    // CUSTOM METHOD - Output slots
    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemHandler.getResource(getOutputSlot()).isEmpty() ||
               itemHandler.getResource(getOutputSlot()).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getResource(getOutputSlot()).isEmpty()
                       ? 64 : itemHandler.getResource(getOutputSlot()).getMaxStackSize();
        int currentCount = itemHandler.getResource(getOutputSlot()).toStack().getCount();
        return maxCount >= currentCount + count;
    }

    @Override public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Nullable @Override public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}