package com.msvdaamen.blocks;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.tileentities.TileEntityInv;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class BasicBlock extends Block {

    public BasicBlock(String name) {
        super(Material.IRON);
        setUnlocalizedName(Drawbridges.MODID + '.' + name);
        setRegistryName(name);
        setCreativeTab(Drawbridges.drawbridgetab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if(world.getTileEntity(pos) != null) {
            if(world.getTileEntity(pos) instanceof TileEntityInv) {
                TileEntityInv tile = (TileEntityInv)world.getTileEntity(pos);
                for(int i = 0 ; i < tile.getItemStackHandler().getSlots(); i++) {
                    ItemStack stack = tile.getItemStackHandler().getStackInSlot(i);
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack));
                }
            }
        }
    }

    public ItemStack getItemstacAtPos(World world, BlockPos pos) {
        return new ItemStack(world.getBlockState(pos).getBlock());
    }
}
