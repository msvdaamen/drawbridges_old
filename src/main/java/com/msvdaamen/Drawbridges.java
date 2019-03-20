package com.msvdaamen;


import com.msvdaamen.init.ModBlocks;
import com.msvdaamen.init.ModRecipes;
import com.msvdaamen.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Drawbridges.MODID, name = Drawbridges.MODNAME, version = Drawbridges.VERSION)
public class Drawbridges {

    public static final String MODID = "drawbridges";
    public static final String MODNAME = "Drawbridges";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.msvdaamen.proxy.ClientProxy", serverSide = "com.msvdaamen.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Drawbridges instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        ModRecipes.init();
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    public static CreativeTabs drawbridgetab = new CreativeTabs("drawbridges") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.drawbridge);
        }
    };
}
