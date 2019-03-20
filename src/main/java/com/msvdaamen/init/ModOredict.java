package com.msvdaamen.init;

import com.msvdaamen.items.ModIngots;
import com.msvdaamen.items.ModPlate;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModOredict {

    public static void registerOredict() {
        if(!Config.useVanillaRecipe) {

            for(int i = 0; i < ModIngots.values().length; i++) {
                OreDictionary.registerOre("ingot" + ModIngots.values()[i].getName(), new ItemStack(ModItems.ingots, 1, i));
            }
            for(int i = 0; i < ModPlate.values().length; i++) {
                OreDictionary.registerOre("plate" + ModPlate.values()[i].getName(), new ItemStack(ModItems.plates, 1, i));
            }
            OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.oreCopper, 1));
            OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.oreTin, 1));
        }
    }
}
