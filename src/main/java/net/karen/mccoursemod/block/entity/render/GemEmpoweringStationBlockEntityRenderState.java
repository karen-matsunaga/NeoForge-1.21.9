package net.karen.mccoursemod.block.entity.render;

import net.karen.mccoursemod.block.entity.GemEmpoweringStationBlockEntity;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GemEmpoweringStationBlockEntityRenderState extends BlockEntityRenderState {
    public GemEmpoweringStationBlockEntity gemEmpoweringStationBlockEntity;
    public ItemStackRenderState item = new ItemStackRenderState();
    public ItemStack itemStack;
    public Level level;
}