package net.karen.top.worldgen.tree;

import net.karen.top.Top;
import net.karen.top.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower BLOODWOOD = new TreeGrower(Top.MOD_ID + ":bloodwood",
           Optional.empty(), Optional.of(ModConfiguredFeatures.BLOODWOOD_KEY), Optional.empty());

    public static final TreeGrower WALNUT = new TreeGrower(Top.MOD_ID + ":walnut",
           Optional.empty(), Optional.of(ModConfiguredFeatures.WALNUT_KEY), Optional.empty());
}