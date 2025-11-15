package net.karen.top.entity.layers;

import net.karen.top.util.Utils;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
    // Registry all custom BOATS and CHEST BOATS
    // Custom boat
    public static final ModelLayerLocation WALNUT_BOAT_LAYER = Utils.modelLayer("boat/walnut");

    // Custom chest boat
    public static final ModelLayerLocation WALNUT_CHEST_BOAT_LAYER = Utils.modelLayer("chest_boat/walnut");

    // Registry all custom ELYTRA
    public static final ModelLayerLocation DIAMOND_ELYTRA_LAYER =
           Utils.modelLayer("textures/entity/equipment/wings/diamond_elytra.png");

    public static final ModelLayerLocation EMERALD_ELYTRA_LAYER =
           Utils.modelLayer("textures/entity/equipment/wings/emerald_elytra.png");
}