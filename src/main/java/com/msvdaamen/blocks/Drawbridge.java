package com.msvdaamen.blocks;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.inventory.GuiProxy;
import com.msvdaamen.tileentities.DrawbridgeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Drawbridge extends BasicDrawbridge implements ITileEntityProvider {

    public final int range;
    public final int placementDuration;
    private boolean active;

    public Drawbridge(String name, int range, int placementDuration) {
        super(name);
        this.range = range;
        this.placementDuration = placementDuration;
    }

    @Override
    public void handleTick(World world, BlockPos pos, IBlockState state) {
        if(isTileEntity(world, pos)) {
            DrawbridgeTileEntity tile = (DrawbridgeTileEntity)world.getTileEntity(pos);
            if(!state.getValue(PULSE_MODE)) {
                boolean isActive = isPowerd(world, pos);
                tile.setActive(isActive);
            }
            if(tile.isActive()) {
                placeBlocks(world, pos, tile);
            } else if(tile.getBlocksPlaced() > 0){
                retractBlocks(world, pos, tile);
            }
        }
    }


    public void placeBlocks(World world, BlockPos pos, DrawbridgeTileEntity tile) {
        ItemStack mainSlot = tile.getItemStackHandler().getStackInSlot(tile.MAIN_SLOT);
        if(!mainSlot.isEmpty()) {
            if(mainSlot.getItem() instanceof ItemBlock) {
                EnumFacing facing = world.getBlockState(pos).getValue(FACING);
                int offset = 0;
                for(int i = 0; i < tile.getRange(); i++) {
                    BlockPos blockPos = pos.offset(facing, i + 1);
                    Block blockAtPos = world.getBlockState(blockPos).getBlock();
                    offset++;
                    if(Block.isEqualTo(blockAtPos, Blocks.AIR)) {
                        break;
                    }
                }
                BlockPos blockPos = pos.offset(facing, offset);
                Block blockAtPos = world.getBlockState(blockPos).getBlock();
                if(Block.isEqualTo(blockAtPos, Blocks.AIR)) {
                    if(!mainSlot.isEmpty()) {
                        tile.setSavedStack(mainSlot.copy());
                        world.setBlockState( pos.offset(facing, offset), Block.getBlockFromItem(mainSlot.getItem()).getStateFromMeta(mainSlot.getMetadata()));
                        tile.getItemStackHandler().getStackInSlot(tile.MAIN_SLOT).shrink(1);
                        tile.setBlocksPlaced(offset);
                    }
                }
            }
        }
    }

    public void retractBlocks(World world, BlockPos pos, DrawbridgeTileEntity tile) {
        ItemStack mainSlot = tile.getItemStackHandler().getStackInSlot(tile.MAIN_SLOT);
        if((mainSlot.isEmpty() || (ItemStack.areItemsEqual(mainSlot, tile.getSaveStack())) && tile.getItemStackHandler().getStackInSlot(tile.MAIN_SLOT).getCount() != tile.getItemStackHandler().getSlotLimit(tile.MAIN_SLOT))) {
            EnumFacing facing = world.getBlockState(pos).getValue(FACING);
            int offset = 0;
            for(int i = 1; i <= tile.getBlocksPlaced(); i++) {
                BlockPos blockPos = pos.offset(facing, i);
                Block blockAtPos = world.getBlockState(blockPos).getBlock();
                if(blockAtPos != Block.getBlockFromItem(tile.getSaveStack().getItem())) {
                    break;
                }
                offset++;
            }
            if(offset > 0) {
                if(tile.getItemStackHandler().getStackInSlot(tile.MAIN_SLOT).isEmpty()) {
                    tile.getItemStackHandler().setStackInSlot(tile.MAIN_SLOT, getItemstackWithMeta(world.getBlockState(pos.offset(facing, offset))));
                } else {
                    tile.getItemStackHandler().getStackInSlot(tile.MAIN_SLOT).grow(1);
                }
                tile.setBlocksPlaced(offset - 1);
                world.setBlockToAir(pos.offset(facing, offset));
            } else {
                tile.setBlocksPlaced(0);
            }
        }
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public int getPlacementDuration() {
        return this.placementDuration;
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
        player.openGui(Drawbridges.instance, GuiProxy.DRAWBRIDGE, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void observedNeighborChange(IBlockState observerState, World world, BlockPos observerPos, Block changedBlock, BlockPos changedBlockPos) {
      boolean powerd = isPowerd(world, observerPos);

        if(isTileEntity(world, observerPos)) {
            DrawbridgeTileEntity tile = (DrawbridgeTileEntity)world.getTileEntity(observerPos);
            if(observerState.getValue(PULSE_MODE)) {
                if(powerd) {
                    if(tile.isActive() && !active) {
                        active = true;
                        tile.setActive(false);
                    }else if(!active) {
                        active = true;
                        tile.setActive(true);
                    }
                } else {
                    active = false;
                }
            } else {
                tile.setActive(powerd);
            }
        }
    }

    public boolean isPowerd(World world, BlockPos pos) {
        return world.isBlockPowered(pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new DrawbridgeTileEntity(1, range);
    }

    public boolean isTileEntity (World world, BlockPos pos) {
        if(world.getTileEntity(pos) != null) {
            if(world.getTileEntity(pos) instanceof DrawbridgeTileEntity) {
                return true;
            }
        }
        return false;
    }
}
