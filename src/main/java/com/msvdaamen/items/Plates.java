package com.msvdaamen.items;

import com.msvdaamen.Drawbridges;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;

public class Plates extends baseItem {

    public Plates() {
        setHasSubtypes(true);
        setRegistryName("plate");
        setUnlocalizedName(Drawbridges.MODID  + "." + "plate");
    }

    @Override
    public void initModel() {
        for (int i = 0; i < ModPlate.values().length; ++i) {
            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(this.getRegistryName(), "plate_type="+ModPlate.values()[i].getName().toLowerCase()));
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            for (int i = 0; i < ModPlate.values().length; ++i)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < ModPlate.values().length; ++i)
        {
            if(stack.getItemDamage() == i) {
                return this.getUnlocalizedName() + ModPlate.values()[i].getName();
            }
        }
        return this.getUnlocalizedName();
    }
}


