package com.msvdaamen.drawbridges.creativetabs;

import com.msvdaamen.drawbridges.init.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DrawbridgeCreativeTab {

    public ItemGroup itemGroup = new ItemGroup("drawbridges") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.DRAWBRIDGE);
        }
    };

    public void init() {

    }
}