package com.msvdaamen.proxy;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.blocks.BasicBlock;
import com.msvdaamen.blocks.Drawbridge;
import com.msvdaamen.blocks.DrawbridgeExtended;
import com.msvdaamen.blocks.DrawbridgeAdvanced;
import com.msvdaamen.init.Config;
import com.msvdaamen.init.ModBlocks;
import com.msvdaamen.init.ModItems;
import com.msvdaamen.init.ModOredict;
import com.msvdaamen.inventory.GuiProxy;
import com.msvdaamen.network.PacketHandler;
import com.msvdaamen.tileentities.DrawbridgeAdvancedTileEntity;
import com.msvdaamen.tileentities.DrawbridgeExtendedTileEntity;
import com.msvdaamen.tileentities.DrawbridgeTileEntity;
import com.msvdaamen.world.OreGen;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.Objects;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "drawbridges.cfg"));
        Config.readConfig();
        PacketHandler.registerMessages(Drawbridges.MODID);
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Drawbridges.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
        if(!Config.useVanillaRecipe) {
            GameRegistry.registerWorldGenerator(new OreGen(), 0);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new Drawbridge("drawbridge", getRange(Config.drawbridgeRange), 10));
        GameRegistry.registerTileEntity(DrawbridgeTileEntity.class, new ResourceLocation(Drawbridges.MODID, "_drawbridge"));

        event.getRegistry().register(new DrawbridgeExtended("drawbridge_extended", getRange(Config.extendedDrawbridgeRange), 10));
        GameRegistry.registerTileEntity(DrawbridgeExtendedTileEntity.class, new ResourceLocation(Drawbridges.MODID, "_drawbridge_extended"));

        event.getRegistry().register(new DrawbridgeAdvanced("drawbridge_advanced", 64, 10));
        GameRegistry.registerTileEntity(DrawbridgeAdvancedTileEntity.class, new ResourceLocation(Drawbridges.MODID, "drawbridge_advanced"));

        event.getRegistry().register(new BasicBlock("ore_copper"));
        event.getRegistry().register(new BasicBlock("ore_tin"));

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.drawbridge).setRegistryName(Objects.requireNonNull(ModBlocks.drawbridge.getRegistryName())));
        event.getRegistry().register(new ItemBlock(ModBlocks.drawbridgeExtended).setRegistryName(Objects.requireNonNull(ModBlocks.drawbridgeExtended.getRegistryName())));
        event.getRegistry().register(new ItemBlock(ModBlocks.drawbridgeAdvanced).setRegistryName(Objects.requireNonNull(ModBlocks.drawbridgeAdvanced.getRegistryName())));
        event.getRegistry().register(new ItemBlock(ModBlocks.oreCopper).setRegistryName(Objects.requireNonNull(ModBlocks.oreCopper.getRegistryName())));
        event.getRegistry().register(new ItemBlock(ModBlocks.oreTin).setRegistryName(Objects.requireNonNull(ModBlocks.oreTin.getRegistryName())));
        ModItems.registerItems(event);
        ModOredict.registerOredict();
    }

    private static int getRange(int range) {
        if(range == 0) {
            return 1;
        }
        if(range > 64) {
            return 64;
        }
        return range;
    }
}