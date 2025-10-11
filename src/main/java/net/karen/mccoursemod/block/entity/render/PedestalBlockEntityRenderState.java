package net.karen.mccoursemod.block.entity.render;

import net.karen.mccoursemod.block.entity.PedestalBlockEntity;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PedestalBlockEntityRenderState extends BlockEntityRenderState {
    public PedestalBlockEntity pedestalBlockEntity;
    public final ItemStackRenderState item = new ItemStackRenderState();
    public ItemStack itemStack;
    public Level level;
}