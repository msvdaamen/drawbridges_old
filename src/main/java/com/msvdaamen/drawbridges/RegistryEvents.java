package com.msvdaamen.drawbridges;

import com.msvdaamen.drawbridges.blocks.Drawbridge;
import com.msvdaamen.drawbridges.blocks.DrawbridgeAdvanced;
import com.msvdaamen.drawbridges.blocks.DrawbridgeExtended;
import com.msvdaamen.drawbridges.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
// Event bus for receiving Registry Events)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new Drawbridge());
        event.getRegistry().register(new DrawbridgeExtended());
        event.getRegistry().register(new DrawbridgeAdvanced());
    }
    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        Item.Properties properties = new Item.Properties().group(Drawbridges.tab.itemGroup);
        event.getRegistry().register(new BlockItem(ModBlocks.DRAWBRIDGE, properties).setRegistryName("drawbridge"));
        event.getRegistry().register(new BlockItem(ModBlocks.DRAWBRIDGE_EXTENDED, properties).setRegistryName("drawbridge_extended"));
        event.getRegistry().register(new BlockItem(ModBlocks.DRAWBRIDGE_ADVANCED, properties).setRegistryName("drawbridge_advanced"));
    }

//    @SubscribeEvent
//    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
//
//    }

}