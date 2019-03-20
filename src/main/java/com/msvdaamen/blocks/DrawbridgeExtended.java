package com.msvdaamen.blocks;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.inventory.GuiProxy;
import com.msvdaamen.tileentities.DrawbridgeExtendedTileEntity;
import com.msvdaamen.tileentities.DrawbridgeTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class DrawbridgeExtended extends Drawbridge {

    public DrawbridgeExtended(String name, int range, int placementDuration) {
        super(name, range, placementDuration);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof DrawbridgeTileEntity)) {
            return false;
        }
        player.openGui(Drawbridges.instance, GuiProxy.DRAWBRIDGE_EXTENDED, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new DrawbridgeExtendedTileEntity(1, getRange());
    }
}
