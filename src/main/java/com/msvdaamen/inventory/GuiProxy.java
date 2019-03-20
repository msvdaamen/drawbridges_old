package com.msvdaamen.inventory;

import com.msvdaamen.inventory.container.DrawbridgeAdvancedContainer;
import com.msvdaamen.inventory.container.DrawbridgeContainer;
import com.msvdaamen.inventory.container.DrawbridgeExtendedContainer;
import com.msvdaamen.inventory.gui.DrawbridgeAdvancedGui;
import com.msvdaamen.inventory.gui.DrawbridgeExtendedGui;
import com.msvdaamen.inventory.gui.DrawbridgeGui;
import com.msvdaamen.tileentities.DrawbridgeAdvancedTileEntity;
import com.msvdaamen.tileentities.DrawbridgeExtendedTileEntity;
import com.msvdaamen.tileentities.DrawbridgeTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    public static final int DRAWBRIDGE = 1;
    public static final int DRAWBRIDGE_EXTENDED = 2;
    public static final int DRAWBRIDGE_ADVANCED = 3;


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (ID == DRAWBRIDGE) {
            return new DrawbridgeContainer(player.inventory, (DrawbridgeTileEntity) te);
        }
        if (ID == DRAWBRIDGE_EXTENDED) {
            return new DrawbridgeExtendedContainer(player.inventory, (DrawbridgeExtendedTileEntity) te);
        }
        if (ID == DRAWBRIDGE_ADVANCED) {
            return new DrawbridgeAdvancedContainer(player.inventory, (DrawbridgeAdvancedTileEntity) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (ID == DRAWBRIDGE) {
            DrawbridgeTileEntity drawbridgeTileEntity = (DrawbridgeTileEntity) te;
            return new DrawbridgeGui(drawbridgeTileEntity, new DrawbridgeContainer(player.inventory, drawbridgeTileEntity));
        }
        if (ID == DRAWBRIDGE_EXTENDED) {
            DrawbridgeExtendedTileEntity drawbridgeExtendedTileEntity = (DrawbridgeExtendedTileEntity) te;
            return new DrawbridgeExtendedGui(drawbridgeExtendedTileEntity, new DrawbridgeExtendedContainer(player.inventory, drawbridgeExtendedTileEntity));
        }
        if (ID == DRAWBRIDGE_ADVANCED) {
            DrawbridgeAdvancedTileEntity drawbridgeAdvancedTileEntity = (DrawbridgeAdvancedTileEntity) te;
            return new DrawbridgeAdvancedGui(drawbridgeAdvancedTileEntity, new DrawbridgeAdvancedContainer(player.inventory, drawbridgeAdvancedTileEntity));
        }
        return null;
    }
}