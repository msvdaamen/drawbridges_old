package com.msvdaamen.drawbridges.renderer;

import com.msvdaamen.drawbridges.Drawbridges;
import com.msvdaamen.drawbridges.init.ModBlocks;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Drawbridges.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().getBasePath().equals("textures")) {
            return;
        }
        event.addSprite(new ResourceLocation(Drawbridges.MODID, "block/drawbridge/drawbridge_top"));
        event.addSprite(new ResourceLocation(Drawbridges.MODID, "block/drawbridge/drawbridge_back"));
        event.addSprite(new ResourceLocation(Drawbridges.MODID, "block/drawbridge/drawbridge_sides"));
        event.addSprite(new ResourceLocation(Drawbridges.MODID, "block/drawbridge/drawbridge_sides_turned"));
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        event.getModelRegistry().put(new ModelResourceLocation(ModBlocks.DRAWBRIDGE.getRegistryName(), ""),
                new DrawbridgeBakedModel(DefaultVertexFormats.BLOCK));
        event.getModelRegistry().put(new ModelResourceLocation(ModBlocks.DRAWBRIDGE.getRegistryName(), "inventory"),
                new DrawbridgeBakedModel(DefaultVertexFormats.ITEM));
    }

}
