package com.msvdaamen.setup;

import com.msvdaamen.blocks.Drawbridge;
import com.msvdaamen.blocks.DrawbridgeAdvanced;
import com.msvdaamen.tileentities.DrawbridgeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.msvdaamen.Drawbridges.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Drawbridge> DRAWBRIDGE = BLOCKS.register("drawbridge", Drawbridge::new);
    public static final RegistryObject<Item> DRAWBRIDGE_ITEM = ITEMS.register("drawbridge", () -> new BlockItem(DRAWBRIDGE.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<DrawbridgeTileEntity>> DRAWBRIDGE_TILE = TILE_ENTITIES.register("drawbridge", () -> TileEntityType.Builder.create(DrawbridgeTileEntity::new, DRAWBRIDGE.get()).build(null));

    public static final RegistryObject<DrawbridgeAdvanced> DRAWBRIDGE_EXTENDED = BLOCKS.register("drawbridge_extended", DrawbridgeAdvanced::new);
    public static final RegistryObject<Item> DRAWBRIDGE_EXTENDED_ITEM = ITEMS.register("drawbridge_extended", () -> new BlockItem(DRAWBRIDGE_EXTENDED.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<DrawbridgeAdvanced> DRAWBRIDGE_ADVANCED = BLOCKS.register("drawbridge_advanced", DrawbridgeAdvanced::new);
    public static final RegistryObject<Item> DRAWBRIDGE_ADVANCED_ITEM = ITEMS.register("drawbridge_advanced", () -> new BlockItem(DRAWBRIDGE_ADVANCED.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
}
