package net.karen.top.loot;

import net.karen.top.Top;
import net.karen.top.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ModLootTables {
    private static final Set<ResourceKey<LootTable>> TOP_LOOT_TABLES = new HashSet<>();
    private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(TOP_LOOT_TABLES);

    // Register all CHEST loot tables
    public static final ResourceKey<LootTable> KAUPEN_HOUSE_TREASURE =
           register("chests/kaupen_house_treasure");

    // Register all FISH loot tables
    public static final ResourceKey<LootTable> TOP_FISHING_ROD_TREASURE =
           register("gameplay/" + Top.MOD_ID + "_fishing_rod_treasure");

    // CUSTOM METHOD - Register all CHEST loot tables
    private static ResourceKey<LootTable> register(String name) {
        return register(Utils.rKey(Registries.LOOT_TABLE, Utils.topPath(name)));
    }

    // CUSTOM METHOD - Register all CHEST loot tables on SET
    private static ResourceKey<LootTable> register(ResourceKey<LootTable> name) {
        if (TOP_LOOT_TABLES.add(name)) { return name; }
        else { throw new IllegalArgumentException(name.location() + " is already a registered built-in loot table"); }
    }

    // CUSTOM METHOD - Return all CHEST loot tables
    public static Set<ResourceKey<LootTable>> all() { return IMMUTABLE_LOCATIONS; }
}