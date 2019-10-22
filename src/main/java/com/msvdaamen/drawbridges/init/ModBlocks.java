package com.msvdaamen.drawbridges.init;

import com.msvdaamen.drawbridges.Drawbridges;
import com.msvdaamen.drawbridges.blocks.Drawbridge;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder(Drawbridges.MODID + ":drawbridge")
    public static Drawbridge DRAWBRIDGE;

    @ObjectHolder(Drawbridges.MODID + ":drawbridge_extended")
    public static Drawbridge DRAWBRIDGE_EXTENDED;

    @ObjectHolder(Drawbridges.MODID + ":drawbridge_advanced")
    public static Drawbridge DRAWBRIDGE_ADVANCED;
}
