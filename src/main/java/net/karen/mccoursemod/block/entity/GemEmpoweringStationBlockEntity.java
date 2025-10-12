//package net.karen.mccoursemod.block.entity;
//
//import net.karen.mccoursemod.item.ModItems;
//import net.karen.mccoursemod.recipe.GemEmpoweringStationRecipe;
//import net.karen.mccoursemod.recipe.GemEmpoweringStationRecipeInput;
//import net.karen.mccoursemod.screen.custom.GemEmpoweringStationMenu;
//import net.karen.mccoursemod.util.ModEnergyStorage;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.Connection;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.protocol.Packet;
//import net.minecraft.network.protocol.game.ClientGamePacketListener;
//import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
//import net.minecraft.world.Containers;
//import net.minecraft.world.MenuProvider;
//import net.minecraft.world.SimpleContainer;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.inventory.AbstractContainerMenu;
//import net.minecraft.world.inventory.ContainerData;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.RecipeHolder;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.storage.ValueInput;
//import net.minecraft.world.level.storage.ValueOutput;
//import net.neoforged.neoforge.capabilities.Capabilities;
//import net.neoforged.neoforge.fluids.FluidStack;
//import net.neoforged.neoforge.transfer.energy.EnergyHandler;
//import net.neoforged.neoforge.transfer.fluid.FluidResource;
//import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
//import net.neoforged.neoforge.transfer.item.ItemResource;
//import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
//import net.neoforged.neoforge.transfer.transaction.Transaction;
//import org.jetbrains.annotations.NotNull;
//import javax.annotation.Nullable;
//import java.util.Optional;
//
//public class GemEmpoweringStationBlockEntity extends BlockEntity implements MenuProvider {
//    // Custom block entity GUI
//    private final ItemStacksResourceHandler itemHandler = new ItemStacksResourceHandler(4) {
//        @Override
//        protected void onContentsChanged(int index, @NotNull ItemStack previousContents) {
//            setChanged();
//            hasLevel(level);
//        }
//
//        @Override
//        public boolean isValid(int index, @NotNull ItemResource resource) { // An item inserted it is valid
//            return switch (index) {
//                case 0 -> true; // Input an item = Item input slot
//                // Input a fluid item = Fluid input slot
//                case 1 -> resource.toStack().getCapability(Capabilities.Fluid.ITEM, null).isValid(index, FluidResource.of(getFluid()));
//                case 2 -> resource.getItem() == ModItems.KOHLRABI.get(); // Transform an item on energy = Energy item slot
//                case 3 -> false; // Output an item = Item output slot
//                default -> super.isValid(index, resource);
//            };
//        }
//    };
//
//    // Constants are the items inserted on custom block entity
//    private static final int INPUT_SLOT = 0;
//    private static final int FLUID_INPUT_SLOT = 1;
//    private static final int ENERGY_ITEM_SLOT = 2;
//    private static final int OUTPUT_SLOT = 3;
//
//    // Progress bar when an item transform on other
//    protected final ContainerData data;
//    private int progress = 0;
//    private int maxProgress = 78;
//
//    // Added DEFAULT values
//    private int energyAmount = 0;
//    private FluidStack neededFluidStack = FluidStack.EMPTY;
//
//    // Progress bar when an item transform on custom energy storage - CLIENT and SERVER is synchronized
//    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();
//
//    private final FluidStacksResourceHandler FLUID_TANK = createFluidTank();
//
//    private FluidStacksResourceHandler createFluidTank() {
//        return new FluidStacksResourceHandler(1, 64000) {
//            @Override
//            protected void onContentsChanged(int index, @NotNull FluidStack previousContents) {
//                setChanged();
//                hasLevel(level);
//            }
//
//            @Override
//            public boolean isValid(int index, @NotNull FluidResource resource) {
//                return true;
//            }
//        };
//    }
//
//    private ModEnergyStorage createEnergyStorage() {
//        return new ModEnergyStorage(64000, 200) {
//            @Override
//            public void onEnergyChanged() {
//                setChanged();
//                Level level = getLevel();
//                if (level != null) {
//                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
//                }
//            }
//        };
//    }
//
//    public ItemStack getRenderStack() {
//        ItemStack stack = itemHandler.getResource(OUTPUT_SLOT).toStack();
//        if (stack.isEmpty()) { stack = itemHandler.getResource(INPUT_SLOT).toStack(); }
//        return stack;
//    }
//
//    // Variables progress and maxProgress synchronization
//    public GemEmpoweringStationBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntities.GEM_EMPOWERING_STATION_BE.get(), pos, state);
//        this.data = new ContainerData() {
//            @Override
//            public int get(int pIndex) {
//                return switch (pIndex) {
//                    case 0 -> GemEmpoweringStationBlockEntity.this.progress;
//                    case 1 -> GemEmpoweringStationBlockEntity.this.maxProgress;
//                    default -> 0;
//                };
//            }
//
//            @Override
//            public void set(int pIndex, int pValue) {
//                switch (pIndex) {
//                    case 0 -> GemEmpoweringStationBlockEntity.this.progress = pValue;
//                    case 1 -> GemEmpoweringStationBlockEntity.this.maxProgress = pValue;
//                }
//            }
//
//            // Two integers to synchronize = progress and maxProgress int variables
//            @Override
//            public int getCount() { return 2; }
//        };
//    }
//
//    // Restore energy storage saved
//    public EnergyHandler getEnergyStorage() { return this.ENERGY_STORAGE; }
//
//    public FluidStack getFluid() { return FLUID_TANK.getResource(0).toStack(64000); }
//
//    // Drops all items on inventory
//    public void drops() {
//        SimpleContainer inventory = new SimpleContainer(itemHandler.size());
//        for (int i = 0; i < itemHandler.size(); i++) { inventory.setItem(i, itemHandler.getResource(i).toStack()); }
//        if (this.level != null) { Containers.dropContents(this.level, this.worldPosition, inventory); }
//    }
//
//    // Name that to show in screen
//    @Override
//    public @NotNull Component getDisplayName() { return Component.literal("Gem Empowering Station"); }
//
//    @Override
//    public @Nullable AbstractContainerMenu createMenu(int containerId,
//                                                      @NotNull Inventory inventory,
//                                                      @NotNull Player player) {
//        return new GemEmpoweringStationMenu(containerId, inventory, this, this.data);
//    }
//
//    // Save all items of inventory
//    @Override
//    protected void saveAdditional(@NotNull ValueOutput output) {
//        itemHandler.serialize(output);
//        output.putInt("gem_empowering_station.progress", progress);
//        output.putInt("gem_empowering_station.max_progress", maxProgress);
//        output.putInt("gem_empowering_station.energy_amount", energyAmount);
//        neededFluidStack.writeToNBT(tag);
//        output.putInt("energy", ENERGY_STORAGE.getAmountAsInt());
//        FLUID_TANK.serialize(output);
//        super.saveAdditional(output);
//    }
//
//    @Override
//    protected void loadAdditional(@NotNull ValueInput input) {
//        super.loadAdditional(input);
//        itemHandler.deserialize(input);
//        progress = input.getIntOr("gem_empowering_station.progress", 0);
//        maxProgress = input.getIntOr("gem_empowering_station.max_progress", 0);
//        energyAmount = input.getIntOr("gem_empowering_station.energy_amount", 0);
//        neededFluidStack = FluidStack.fixedAmountCodec(input.getIntOr("fluid", 0));
//        ENERGY_STORAGE.setEnergy(input.getIntOr("energy", 0));
//        FLUID_TANK.deserialize(input);
//    }
//
//    // Custom block entity class to work on Server side
//    public void tick(Level level, BlockPos pos, BlockState state) {
//        fillUpOnEnergy(); // This is a "placeholder" for getting energy through wires or similar
//        fillUpOnFluid();
//        if (isOutputSlotEmptyOrReceivable() && hasRecipe()) {
//            increaseCraftingProcess();
//            extractEnergy();
//            setChanged(level, pos, state);
//            if (hasProgressFinished()) {
//                craftItem();
//                extractFluid();
//                resetProgress();
//            }
//        }
//        else { resetProgress(); }
//    }
//
//    private void extractFluid() {
//        this.FLUID_TANK.getResource(0).toStack(neededFluidStack.getAmount());
//    }
//
//    private void fillUpOnFluid() {
//        if (hasFluidSourceInSlot()) { transferItemFluidToTank(); }
//    }
//
//    private void transferItemFluidToTank() {
//        this.itemHandler.getResource(GemEmpoweringStationBlockEntity.FLUID_INPUT_SLOT).toStack().getCapability(
//                Capabilities.Fluid.ITEM).ifPresent(iFluidHandlerItem -> {
//            int drainAmount = Math.min(this.FLUID_TANK.getResource(0).getFluid(), 1000);
//
//            FluidStack stack = iFluidHandlerItem.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
//            stack = iFluidHandlerItem.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
//            fillTankWithFluid(stack, iFluidHandlerItem.getContainer());
//        });
//    }
//
//    private void fillTankWithFluid(FluidStack stack, ItemStack container) {
//        this.FLUID_TANK.getResource(0).fill(new FluidStack(stack.getFluid(), stack.getAmount()), IFluidHandler.FluidAction.EXECUTE);
//        try (Transaction tx = Transaction.openRoot()) {
//            this.itemHandler.extract(FLUID_INPUT_SLOT, ItemResource.of(container), 1, tx);
//            this.itemHandler.insert(FLUID_INPUT_SLOT, ItemResource.of(container), stack.getAmount(), tx);
//            tx.commit();
//        }
//    }
//
//    private boolean hasFluidSourceInSlot() {
//        return !this.itemHandler.getResource(GemEmpoweringStationBlockEntity.FLUID_INPUT_SLOT).isEmpty() &&
//                this.itemHandler.getResource(GemEmpoweringStationBlockEntity.FLUID_INPUT_SLOT)
//                                .toStack().getCapability(Capabilities.Fluid.ITEM) != null &&
//                this.itemHandler.getResource(GemEmpoweringStationBlockEntity.FLUID_INPUT_SLOT)
//                                .toStack().getCapability(Capabilities.Fluid.ITEM).getFluidInTank(0).isEmpty() ||
//                FluidUtil.tryFluidTransfer(itemHandler.getResource(GemEmpoweringStationBlockEntity.FLUID_INPUT_SLOT)
//                                .toStack().getCapability(Capabilities.Fluid.ITEM),
//                        FLUID_TANK, Integer.MAX_VALUE, false) != FluidStack.EMPTY;
//    }
//
//    private void extractEnergy() {
//        try (Transaction tx = Transaction.openRoot()) {
//            this.ENERGY_STORAGE.extract(energyAmount, tx);
//            tx.commit();
//        }
//    }
//
//    private void fillUpOnEnergy() {
//        if (hasEnergyItemInSlot()) {
//            try (Transaction tx = Transaction.openRoot()) {
//                this.ENERGY_STORAGE.insert(3200, tx);
//                tx.commit();
//            }
//        }
//    }
//
//    private boolean hasEnergyItemInSlot() {
//        return !this.itemHandler.getResource(GemEmpoweringStationBlockEntity.ENERGY_ITEM_SLOT).isEmpty() &&
//                this.itemHandler.getResource(GemEmpoweringStationBlockEntity.ENERGY_ITEM_SLOT).getItem() ==
//                ModItems.KOHLRABI.get();
//    }
//
//    private void craftItem() {
//        Optional<RecipeHolder<GemEmpoweringStationRecipe>> recipe = getCurrentRecipe();
//        assert getLevel() != null && recipe.isPresent();
//        ItemStack resultItem = recipe.get().value().assemble(new GemEmpoweringStationRecipeInput(),
//                                                             getLevel().registryAccess());
//        try (Transaction tx = Transaction.openRoot()) {
//            this.itemHandler.extract(INPUT_SLOT,
//                                     ItemResource.of(recipe.get().value().getInputItems().getFirst().getValues().get(0)),
//                                     1, tx); // Input slot
//            this.itemHandler.set(OUTPUT_SLOT, ItemResource.of(resultItem.getItem()),
//                                 this.itemHandler.getResource(OUTPUT_SLOT).toStack().getCount() +
//                                 resultItem.getCount()); // Output slot
//            tx.commit();
//        }
//    }
//
//    private void resetProgress() { this.progress = 0; }
//
//    private boolean hasProgressFinished() { return this.progress >= this.maxProgress; }
//
//    private void increaseCraftingProcess() { this.progress++; }
//
//    private boolean hasRecipe() {
//        Optional<RecipeHolder<GemEmpoweringStationRecipe>> recipe = getCurrentRecipe();
//        if (recipe.isEmpty()) { return false; }
//        maxProgress = recipe.get().value().getCraftTime();
//        energyAmount = recipe.get().value().getEnergyAmount();
//        neededFluidStack = recipe.get().value().getFluidStack();
//        assert getLevel() != null;
//        ItemStack resultItem = recipe.get().value().assemble(new GemEmpoweringStationRecipeInput(),
//                                                             getLevel().registryAccess());
//        return canInsertAmountIntoOutputSlot(resultItem.getCount())
//                && canInsertItemIntoOutputSlot(resultItem.getItem()) && hasEnoughEnergyToCraft()
//                && hasEnoughFluidToCraft();
//    }
//
//    private boolean hasEnoughFluidToCraft() {
//        return this.FLUID_TANK.getAmountAsInt(0) >= neededFluidStack.getAmount();
//    }
//
//    private boolean hasEnoughEnergyToCraft() {
//        return this.ENERGY_STORAGE.getAmountAsInt() >= energyAmount * maxProgress;
//    }
//
//    // Verify all custom recipes if are added or not inserted
//    private Optional<RecipeHolder<GemEmpoweringStationRecipe>> getCurrentRecipe() {
//        SimpleContainer inventory = new SimpleContainer(this.itemHandler.size());
//        for (int i = 0; i < this.itemHandler.size(); i++) {
//            inventory.setItem(i, this.itemHandler.getResource(i).toStack());
//        }
//        Level level1 = this.level;
//        assert level1 != null && level1.getServer() != null;
//        return level1.getServer().getRecipeManager()
//                                 .getRecipeFor(new GemEmpoweringStationRecipeInput(),
//                                               inventory, level);
//    }
//
//    private boolean canInsertItemIntoOutputSlot(Item item) {
//        return this.itemHandler.getResource(OUTPUT_SLOT).isEmpty() ||
//               this.itemHandler.getResource(OUTPUT_SLOT).is(item);
//    }
//
//    private boolean canInsertAmountIntoOutputSlot(int count) {
//        return this.itemHandler.getResource(OUTPUT_SLOT).getMaxStackSize() >=
//               this.itemHandler.getResource(OUTPUT_SLOT).toStack().getCount() + count;
//    }
//
//    private boolean isOutputSlotEmptyOrReceivable() {
//        return this.itemHandler.getResource(OUTPUT_SLOT).isEmpty() ||
//               this.itemHandler.getResource(OUTPUT_SLOT).toStack().getCount() <
//               this.itemHandler.getResource(OUTPUT_SLOT).getMaxStackSize();
//    }
//
//    // Save and restore on disk the energy storage
//    @Override
//    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
//        return ClientboundBlockEntityDataPacket.create(this);
//    }
//
//    @Override
//    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
//        return saveWithoutMetadata(registries);
//    }
//
//    @Override
//    public void onDataPacket(@NotNull Connection net, @NotNull ValueInput valueInput) {
//        super.onDataPacket(net, valueInput);
//    }
//
//    private void hasLevel(Level level) {
//        if (level != null) {
//            if (!level.isClientSide()) {
//                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
//            }
//        }
//    }
//}