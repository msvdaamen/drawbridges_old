package com.msvdaamen.tileentities;

import com.msvdaamen.Drawbridges;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DrawbridgeTileEntity extends TileEntityInv {

    private ItemStack saveStack = ItemStack.EMPTY;
    private boolean active = false;
    private int blocksPlaced;
    public final int MAIN_SLOT = 0;

    public DrawbridgeTileEntity() {}

    public DrawbridgeTileEntity(int invSize, int range) {
        super(invSize, range);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if(compound.hasKey("save_stack")) {
            this.saveStack = new ItemStack(compound.getCompoundTag("save_stack"));
        }
        if(compound.hasKey("active")) {
            this.active = compound.getBoolean("active");
        }

        if(compound.hasKey("blocks_placed")) {
            this.blocksPlaced = compound.getInteger("blocks_placed");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if(!this.saveStack.isEmpty()) {
            compound.setTag("save_stack", saveStack.serializeNBT());
        }
        compound.setBoolean("active", this.active);
        compound.setInteger("blocks_placed", this.blocksPlaced);
        return compound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    public ItemStack getSaveStack() {
        return this.saveStack;
    }

    public void setSavedStack(ItemStack saveStack) {
        this.saveStack = saveStack;
        this.saveStack.setCount(1);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getBlocksPlaced() {
        return this.blocksPlaced;
    }

    public void setBlocksPlaced(int placed) {
        this.blocksPlaced = placed;
    }

    public void growBlocksPlaced() {
        Drawbridges.logger.info(this.blocksPlaced < this.getRange());
        if(this.blocksPlaced < this.getRange()) {
            this.blocksPlaced++;
        }
    }

    public void shrinkBlocksPlaced() {
        if(this.blocksPlaced > 0) {
            this.blocksPlaced--;
        }
    }
}
