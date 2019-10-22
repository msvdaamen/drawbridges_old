package com.msvdaamen.drawbridges.tileentities;

import com.msvdaamen.drawbridges.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class DrawbridgeTileEntity extends BasicTileEntity {

    public static final ModelProperty<BlockState> CAMMO = new ModelProperty<>();

    private BlockState cammo;

    public DrawbridgeTileEntity() {
        super(ModBlocks.DRAWBRIDGE_TILE);
    }

    public void setCammo(BlockState cammo) {
        this.cammo = cammo;
        markDirty();
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tag = new CompoundNBT();
        if (cammo != null) {
            tag.put("cammo", NBTUtil.writeBlockState(cammo));
        }
        return new SUpdateTileEntityPacket(pos, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        BlockState oldMimic = cammo;
        CompoundNBT tag = pkt.getNbtCompound();
        if (tag.contains("cammo")) {
            cammo = NBTUtil.readBlockState(tag.getCompound("cammo"));
            if (!Objects.equals(oldMimic, cammo)) {
                ModelDataManager.requestModelDataRefresh(this);
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
            }
        }
    }

    @Nonnull
    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder()
                .withInitial(CAMMO, cammo)
                .build();
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
        if (tag.contains("cammo")) {
            cammo = NBTUtil.readBlockState(tag.getCompound("cammo"));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        if (cammo != null) {
            tag.put("cammo", NBTUtil.writeBlockState(cammo));
        }
        return super.write(tag);
    }
}
