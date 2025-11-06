package net.karen.top.block.entity.renderstate;

import net.karen.top.block.entity.GemEmpoweringStationBlockEntity;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GemEmpoweringStationBlockEntityRenderState extends BlockEntityRenderState {
    public GemEmpoweringStationBlockEntity gemEmpoweringStationBlockEntity;
    public ItemStackRenderState item = new ItemStackRenderState();
    public ItemStack itemStack;
    public Level level;
    public BlockPos gemEmpoweringStationBEBlockPos;
    public BlockState gemEmpoweringStationBEBlockState;
}