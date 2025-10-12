//package net.karen.mccoursemod.util;
//
//import net.minecraft.core.Direction;
//import net.neoforged.neoforge.common.util.Lazy;
//import net.neoforged.neoforge.transfer.item.ItemAccessItemHandler;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//public class InventoryDirectionWrapper {
//    public Map<Direction, Lazy<WrappedHandler>> directionsMap;
//
//    public InventoryDirectionWrapper(ItemAccessItemHandler handler,
//                                     InventoryDirectionEntry... entries) {
//        directionsMap = new HashMap<>();
//        for (var x : entries) {
//            directionsMap.put(x.direction, Lazy.of(() -> new WrappedHandler(handler, (i) ->
//                                                   Objects.equals(i, x.slotIndex),
//                                                   (i, s) -> x.canInsert)));
//        }
//    }
//}