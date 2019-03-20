package com.msvdaamen.tileentities;

import com.msvdaamen.Drawbridges;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class BasicTileEntity extends TileEntity {

    public BasicTileEntity() {}

    public Block getBlockAtPos(BlockPos pos) {
        return getWorld().getBlockState(pos).getBlock();
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }
}
