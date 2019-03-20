package com.msvdaamen.items;


import com.msvdaamen.Drawbridges;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;

public class Ingots extends baseItem {

    public Ingots() {
        setHasSubtypes(true);
        setRegistryName("ingot");
        setUnlocalizedName(Drawbridges.MODID  + "." + "ingot");
    }

    @Override
    public void initModel() {
        for (int i = 0; i < ModIngots.values().length; ++i) {
            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(this.getRegistryName(), "ingot_type="+ModIngots.values()[i].getName().toLowerCase()));
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            for (int i = 0; i < ModIngots.values().length; ++i)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < ModIngots.values().length; ++i)
        {
            if(stack.getItemDamage() == i) {
                return this.getUnlocalizedName() + ModIngots.values()[i].getName();
            }
        }
        return this.getUnlocalizedName();
    }
}
