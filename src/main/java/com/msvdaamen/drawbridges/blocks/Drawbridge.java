package com.msvdaamen.drawbridges.blocks;

import com.msvdaamen.drawbridges.tileentities.DrawbridgeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Drawbridge extends BasicDrawbridge {

    public Drawbridge() {
        super();
        setRegistryName("drawbridge");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DrawbridgeTileEntity();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack item = player.getHeldItem(hand);
        if (!item.isEmpty() && item.getItem() instanceof BlockItem && !(((BlockItem) item.getItem()).getBlock() instanceof Drawbridge)) {
            if (!world.isRemote) {
                TileEntity te = world.getTileEntity(pos);
                if (te instanceof DrawbridgeTileEntity) {
                    BlockState mimicState = ((BlockItem) item.getItem()).getBlock().getDefaultState();
                    ((DrawbridgeTileEntity) te).setCammo(mimicState);
                }
            }
            return true;
        }
        return super.onBlockActivated(state, world, pos, player, hand, result);
    }
}
