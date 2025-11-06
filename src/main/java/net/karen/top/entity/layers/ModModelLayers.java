package net.karen.top.entity.layers;

import net.karen.top.Top;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    // Registry all custom BOATS and CHEST BOATS
    public static final ModelLayerLocation WALNUT_BOAT_LAYER =
           new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Top.MOD_ID, "boat/walnut"),
                                  "main"); // Custom boat

    public static final ModelLayerLocation WALNUT_CHEST_BOAT_LAYER =
           new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Top.MOD_ID, "chest_boat/walnut"),
                                  "main"); // Custom chest boat

    // Registry all custom ELYTRA
    public static final ModelLayerLocation DIAMOND_ELYTRA_LAYER =
           new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Top.MOD_ID,
                                                                        "textures/entity/equipment/wings/diamond_elytra.png"),
                                  "main");
}