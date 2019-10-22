package com.msvdaamen.drawbridges.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class Drawbridge extends Block {

    public Drawbridge() {
        super(
                Properties.create(Material.IRON)
                .hardnessAndResistance(2F)
        );
        setRegistryName("drawbridge");
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return true;
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return false;
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return false;
    }

    @Override
    public int getStrongPower(BlockState p_176211_1_, IBlockReader p_176211_2_, BlockPos p_176211_3_, Direction p_176211_4_) {
        return 0;
    }

    @Override
    public int getWeakPower(BlockState p_180656_1_, IBlockReader p_180656_2_, BlockPos p_180656_3_, Direction p_180656_4_) {
        return 0;
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, IWorldReader world, BlockPos pos, Direction side) {
        return false;
    }
}
