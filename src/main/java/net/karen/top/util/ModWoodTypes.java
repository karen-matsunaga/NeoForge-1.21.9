package net.karen.top.util;

import net.karen.top.Top;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    // CUSTOM Wood Types - SIGN block
    // BLOODWOOD
    public static final WoodType BLOODWOOD =
           WoodType.register(new WoodType(Top.MOD_ID + ":bloodwood", BlockSetType.OAK));

    // WALNUT
    public static final WoodType WALNUT =
           WoodType.register(new WoodType(Top.MOD_ID + ":walnut", BlockSetType.OAK));
}