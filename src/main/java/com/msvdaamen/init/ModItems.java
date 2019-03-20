package com.msvdaamen.init;

import com.msvdaamen.items.Hammer;
import com.msvdaamen.items.Ingots;
import com.msvdaamen.items.Plates;
import com.msvdaamen.items.StaticItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static Ingots ingots = new Ingots();
    public static Plates plates = new Plates();
    public static Hammer hammer = new Hammer();
    public static StaticItem bronze_alloy = new StaticItem("bronze_alloy");

    public static void registerItems(RegistryEvent.Register<Item> event) {
//        if(!Config.useVanillaRecipe) {
            event.getRegistry().register(ingots);
            event.getRegistry().register(plates);
            event.getRegistry().register(hammer);
            event.getRegistry().register(bronze_alloy);
//        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
//        if(!Config.useVanillaRecipe) {
            ingots.initModel();
            plates.initModel();
            hammer.initModel();
            bronze_alloy.initModel();
//        }
    }
}
