package com.msvdaamen.drawbridges.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Drawbridge extends Block {
    public Drawbridge() {
        super(
                Properties.create(Material.IRON)
                .hardnessAndResistance(2F)
        );
        setRegistryName("drawbridge");
    }
}
