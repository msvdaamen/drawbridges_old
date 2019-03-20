package com.msvdaamen.blocks;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BasicDrawbridge extends TileBlock {

    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static final PropertyBool PULSE_MODE = PropertyBool.create("pulse_mode");

    public BasicDrawbridge(String name) {
        super(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(PULSE_MODE, false));
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
        world.scheduleUpdate(pos, this, getPlacementDuration());
    }

    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector(
                (float) (entity.posX - clickedBlock.getX()),
                (float) (entity.posY - clickedBlock.getY()),
                (float) (entity.posZ - clickedBlock.getZ()));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(PULSE_MODE, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(PULSE_MODE)).booleanValue()) {
            i |= 8;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, PULSE_MODE});
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return 0;
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return 0;
    }

    @Override
    public boolean shouldCheckWeakPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return false;
    }

    @Override
    public int tickRate(World worldIn) {
        return getPlacementDuration();
    }

    @Override
    public boolean getTickRandomly() {
        return false;
    }

    public abstract void handleTick(World world, BlockPos pos, IBlockState state);

    public abstract int getRange();

    public abstract int getPlacementDuration();

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if(!world.isRemote) {
            handleTick(world, pos, state);
        }
        world.scheduleUpdate(pos, this, getPlacementDuration());
    }

    public ItemStack getItemstackWithMeta(IBlockState block) {
        return new ItemStack(block.getBlock(), 1, block.getBlock().getMetaFromState(block));
    }
}
