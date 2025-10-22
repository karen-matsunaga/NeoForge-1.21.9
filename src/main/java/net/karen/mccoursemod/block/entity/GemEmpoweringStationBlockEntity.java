package net.karen.mccoursemod.block.entity;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.recipe.GemEmpoweringStationRecipe;
import net.karen.mccoursemod.recipe.GemEmpoweringStationRecipeInput;
import net.karen.mccoursemod.recipe.ModRecipes;
import net.karen.mccoursemod.screen.custom.GemEmpoweringStationMenu;
import net.karen.mccoursemod.util.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
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
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class GemEmpoweringStationBlockEntity extends BlockEntity implements MenuProvider {
    // Custom block entity GUI
    private final ItemStacksResourceHandler itemHandler = new ItemStacksResourceHandler(3) {
        @Override
        protected void onContentsChanged(int index, @NotNull ItemStack previousContents) {
            setChanged();
            hasLevel(level);
        }

        @Override
        public boolean isValid(int index, @NotNull ItemResource resource) { // An item inserted it is valid
            return switch (index) {
                case 0 -> true; // Input an item = Item input slot
                case 1 -> resource.getItem() == ModItems.KOHLRABI.get(); // Transform an item on energy = Energy item slot
                case 2 -> false; // Output an item = Item output slot
                default -> super.isValid(index, resource);
            };
        }
    };

    // Constants are the items inserted on custom block entity
    private static final int INPUT_SLOT = 0;
    private static final int ENERGY_ITEM_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    // Progress bar when an item transform on other
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    // Added DEFAULT energy values
    private int energyAmount = 0;

    // Progress bar when an item transform on custom ENERGY storage - CLIENT and SERVER is synchronized
    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();

    private ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(64000, 200) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                hasLevel(level);
            }
        };
    }

    // CUSTOM METHOD - Item slot index
    public ItemResource itemHandler(int index) {
        return this.itemHandler.getResource(index);
    }

    public ItemStack getRenderStack() {
        ItemStack stack = itemHandler(OUTPUT_SLOT).toStack();
        if (stack.isEmpty()) { stack = itemHandler(INPUT_SLOT).toStack(); }
        return stack;
    }

    public ItemStacksResourceHandler getItemHandler() {
        return itemHandler;
    }

    // Variables progress and maxProgress synchronization
    public GemEmpoweringStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEM_EMPOWERING_STATION_BE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemEmpoweringStationBlockEntity.this.progress;
                    case 1 -> GemEmpoweringStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemEmpoweringStationBlockEntity.this.progress = value;
                    case 1 -> GemEmpoweringStationBlockEntity.this.maxProgress = value;
                }
            }

            // Two integers to synchronize = progress and maxProgress int variables
            @Override
            public int getCount() { return 2; }
        };
    }

    // Restore energy storage saved
    public EnergyHandler getEnergyStorage() { return this.ENERGY_STORAGE; }

    // Drops all items on inventory
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.size());
        for (int i = 0; i < itemHandler.size(); i++) {
            inventory.setItem(i, itemHandler(i).toStack(itemHandler.getAmountAsInt(i)));
        }
        if (this.level != null) { Containers.dropContents(this.level, this.worldPosition, inventory); }
    }

    // Name that to show in screen
    @Override
    public @NotNull Component getDisplayName() { return Component.literal("Gem Empowering Station"); }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId,
                                                      @NotNull Inventory inventory, @NotNull Player player) {
        return new GemEmpoweringStationMenu(containerId, inventory, this, this.data);
    }

    @Override
    public void preRemoveSideEffects(@NotNull BlockPos pos, @NotNull BlockState state) {
        drops();
        super.preRemoveSideEffects(pos, state);
    }

    // Save all items of inventory
    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);
        itemHandler.serialize(output);
        output.putInt("gem_empowering_station.progress", progress);
        output.putInt("gem_empowering_station.max_progress", maxProgress);
        output.putInt("gem_empowering_station.energy_amount", energyAmount);
        output.putInt("energy", ENERGY_STORAGE.getAmountAsInt());
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        itemHandler.deserialize(input);
        progress = input.getIntOr("gem_empowering_station.progress", 0);
        maxProgress = input.getIntOr("gem_empowering_station.max_progress", 0);
        energyAmount = input.getIntOr("gem_empowering_station.energy_amount", 0);
        ENERGY_STORAGE.setEnergy(input.getIntOr("energy", 0));
    }

    // Custom block entity class to work on Server side
    public void tick(Level level, BlockPos pos, BlockState state) {
        fillUpOnEnergy(); // This is a "placeholder" for getting energy through wires or similar
        if (hasRecipe()) {
            increaseCraftingProcess();
            extractEnergy();
            setChanged(level, pos, state);
            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        }
        else { resetProgress(); }
    }

    private void extractEnergy() {
        try (Transaction tx = Transaction.openRoot()) {
            this.ENERGY_STORAGE.extract(energyAmount, tx);
            tx.commit();
        }
    }

    private void fillUpOnEnergy() {
        if (hasEnergyItemInSlot()) {
            try (Transaction tx = Transaction.openRoot()) {
                this.ENERGY_STORAGE.insert(3200, tx);
                tx.commit();
            }
        }
    }

    private boolean hasEnergyItemInSlot() {
        return !itemHandler(ENERGY_ITEM_SLOT).isEmpty() && itemHandler(ENERGY_ITEM_SLOT).getItem() == ModItems.KOHLRABI.get();
    }

    private void craftItem() {
        Optional<RecipeHolder<GemEmpoweringStationRecipe>> recipe = getCurrentRecipe();
        if (getLevel() != null && recipe.isPresent()) {
            GemEmpoweringStationRecipe recipes = recipe.get().value();
            ItemStack resultItem = recipes.output();
            try (Transaction tx = Transaction.openRoot()) {
                this.itemHandler.extract(INPUT_SLOT, ItemResource.of(recipes.getInputItems().getFirst().getValues().get(0)),
                                         1, tx); // Input slot
                tx.commit();
                this.itemHandler.set(OUTPUT_SLOT, ItemResource.of(resultItem.getItem()),
                                     itemHandler.getAmountAsInt(OUTPUT_SLOT) + resultItem.getCount()); // Output slot
            }
        }
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 78;
    }

    private boolean hasProgressFinished() { return this.progress >= this.maxProgress; }

    private void increaseCraftingProcess() { this.progress++; }

    private boolean hasRecipe() {
        Optional<RecipeHolder<GemEmpoweringStationRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty() || getLevel() == null) { return false; }
        GemEmpoweringStationRecipe recipes = recipe.get().value();
        maxProgress = recipes.getCraftTime();
        energyAmount = recipes.getEnergyAmount();
        ItemStack resultItem = recipes.output();
        return canInsertAmountIntoOutputSlot(resultItem.getCount()) &&
               canInsertItemIntoOutputSlot(resultItem) && hasEnoughEnergyToCraft();
    }

    private boolean hasEnoughEnergyToCraft() {
        return this.ENERGY_STORAGE.getAmountAsInt() >= energyAmount * maxProgress;
    }

    // Verify all custom recipes if are added or not inserted
    private Optional<RecipeHolder<GemEmpoweringStationRecipe>> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.size());
        for (int i = 0; i < this.itemHandler.size(); i++) {
            inventory.setItem(i, itemHandler(i).toStack(itemHandler.getAmountAsInt(i)));
        }
        Level level = this.level;
        assert level != null;
        MinecraftServer mcServer = level.getServer();
        assert mcServer != null;
        return mcServer.getRecipeManager()
                       .getRecipeFor(ModRecipes.GEM_EMPOWERING_STATION_TYPE.get(),
                                     new GemEmpoweringStationRecipeInput(List.of(inventory.getItem(0),
                                                                                 inventory.getItem(1))),
                                     level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack item) {
        return itemHandler(OUTPUT_SLOT).isEmpty() || itemHandler(OUTPUT_SLOT).getItem() == item.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        ItemResource outputSlot = itemHandler.getResource(OUTPUT_SLOT);
        int maxCount = outputSlot.isEmpty() ? 64 : outputSlot.getMaxStackSize();
        int currentCount = outputSlot.toStack().getCount();
        return maxCount >= currentCount + count;
    }

    // Save and restore on disk the energy storage
    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void onDataPacket(@NotNull Connection net, @NotNull ValueInput valueInput) {
        super.onDataPacket(net, valueInput);
    }

    // CUSTOM METHOD - Level
    private void hasLevel(Level level) {
        if (level != null) {
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    }
}