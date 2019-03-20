package com.msvdaamen.init;

import com.msvdaamen.items.ModIngots;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static void init() {
        GameRegistry.addSmelting(new ItemStack(ModBlocks.oreCopper, 1), new ItemStack(ModItems.ingots, 1, ModIngots.COPPER.getId()), 1.5f);
        GameRegistry.addSmelting(new ItemStack(ModBlocks.oreTin, 1), new ItemStack(ModItems.ingots, 1, ModIngots.TIN.getId()), 1.5f);
        GameRegistry.addSmelting(new ItemStack(ModItems.bronze_alloy, 1), new ItemStack(ModItems.ingots, 1, ModIngots.BRONZE.getId()), 1.5f);
    }
}