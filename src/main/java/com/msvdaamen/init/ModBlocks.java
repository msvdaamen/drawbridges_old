package com.msvdaamen.init;

        import com.msvdaamen.Drawbridges;
        import com.msvdaamen.blocks.BasicBlock;
        import com.msvdaamen.blocks.Drawbridge;
        import com.msvdaamen.blocks.DrawbridgeExtended;
        import com.msvdaamen.blocks.DrawbridgeAdvanced;
        import net.minecraftforge.fml.common.registry.GameRegistry;
        import net.minecraftforge.fml.relauncher.Side;
        import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder(Drawbridges.MODID + ":drawbridge")
    public static Drawbridge drawbridge;

    @GameRegistry.ObjectHolder(Drawbridges.MODID + ":drawbridge_extended")
    public static DrawbridgeExtended drawbridgeExtended;

    @GameRegistry.ObjectHolder(Drawbridges.MODID + ":drawbridge_advanced")
    public static DrawbridgeAdvanced drawbridgeAdvanced;

    @GameRegistry.ObjectHolder(Drawbridges.MODID + ":ore_copper")
    public static BasicBlock oreCopper;

    @GameRegistry.ObjectHolder(Drawbridges.MODID + ":ore_tin")
    public static BasicBlock oreTin;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        drawbridge.initModel();
        drawbridgeExtended.initModel();
        drawbridgeAdvanced.initModel();
        oreCopper.initModel();
        oreTin.initModel();
    }
}
