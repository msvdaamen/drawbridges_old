package com.msvdaamen.drawbridges.init;

import com.msvdaamen.drawbridges.Drawbridges;
import com.msvdaamen.drawbridges.blocks.Drawbridge;
import com.msvdaamen.drawbridges.blocks.DrawbridgeAdvanced;
import com.msvdaamen.drawbridges.blocks.DrawbridgeExtended;
import com.msvdaamen.drawbridges.tileentities.DrawbridgeTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder(Drawbridges.MODID + ":drawbridge")
    public static Drawbridge DRAWBRIDGE;

    @ObjectHolder(Drawbridges.MODID + ":drawbridge")
    public static TileEntityType<DrawbridgeTileEntity> DRAWBRIDGE_TILE;

    @ObjectHolder(Drawbridges.MODID + ":drawbridge_extended")
    public static DrawbridgeExtended DRAWBRIDGE_EXTENDED;

    @ObjectHolder(Drawbridges.MODID + ":drawbridge_advanced")
    public static DrawbridgeAdvanced DRAWBRIDGE_ADVANCED;
}
