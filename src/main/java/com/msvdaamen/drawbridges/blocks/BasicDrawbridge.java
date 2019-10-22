package com.msvdaamen.drawbridges.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class BasicDrawbridge extends Block {

    public BasicDrawbridge() {
        super(
            Properties.create(Material.IRON)
            .hardnessAndResistance(2F)
        );
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
        return Direction.getFacingFromVector(
                (float) (entity.posX - clickedBlock.getX()),
                (float) (entity.posY - clickedBlock.getY()),
                (float) (entity.posZ - clickedBlock.getZ()));
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }


}
