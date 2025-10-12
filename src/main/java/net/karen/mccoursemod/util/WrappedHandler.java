//package net.karen.mccoursemod.util;
//
//import net.minecraft.world.item.ItemStack;
//import net.neoforged.neoforge.transfer.item.ItemAccessItemHandler;
//import net.neoforged.neoforge.transfer.item.ItemResource;
//import net.neoforged.neoforge.transfer.transaction.TransactionContext;
//import org.jetbrains.annotations.NotNull;
//import java.util.function.BiPredicate;
//import java.util.function.Predicate;
//
//public class WrappedHandler extends ItemAccessItemHandler {
//    private final ItemAccessItemHandler handler;
//    private final Predicate<Integer> extract;
//    private final BiPredicate<Integer, ItemStack> insert;
//
//    public WrappedHandler(ItemAccessItemHandler handler, Predicate<Integer> extract,
//                          BiPredicate<Integer, ItemStack> insert) {
//        super();
//        this.handler = handler;
//        this.extract = extract;
//        this.insert = insert;
//    }
//
//    @Override
//    protected @NotNull ItemResource getResourceFrom(@NotNull ItemResource accessResource, int index) {
//        return this.handler.getResource(index);
//    }
//
//    @Override
//    public int size() { return this.handler.size(); }
//
//    @Override
//    public @NotNull ItemResource getResource(int index) { return this.handler.getResource(index); }
//
//    @Override
//    public int insert(ItemResource resource, int amount, @NotNull TransactionContext transaction) {
//        return this.insert.test(size(), resource.toStack()) ?
//               this.handler.insert(size(), resource, amount, transaction) : resource.toStack().getMaxStackSize();
//    }
//
//    @Override
//    public int extract(@NotNull ItemResource resource, int amount, @NotNull TransactionContext transaction) {
//        return this.extract.test(size()) ? this.handler.extract(size(), resource, amount, transaction) : resource.getMaxStackSize();
//    }
//
//    @Override
//    protected int getCapacity(int index, @NotNull ItemResource resource) {
//        return this.handler.getCapacityAsInt(index, resource);
//    }
//
//    @Override
//    public boolean isValid(int index, ItemResource resource) {
//        return this.insert.test(index, resource.toStack()) && this.handler.isValid(index, resource);
//    }
//}