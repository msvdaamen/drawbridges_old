package com.msvdaamen.drawbridges.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DrawbridgeExtended extends Block {

    public DrawbridgeExtended() {
        super(
                Properties.create(Material.IRON)
                        .hardnessAndResistance(2F)
        );
        setRegistryName("drawbridge_extended");
    }
}
