package com.msvdaamen.setup;

import com.msvdaamen.Drawbridges;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Drawbridges.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
//        ScreenManager.registerFactory(Registration.FIRSTBLOCK_CONTAINER.get(), FirstBlockScreen::new);
//        RenderingRegistry.registerEntityRenderingHandler(Registration.WEIRDMOB.get(), WeirdMobRenderer::new);
//        ModelLoaderRegistry.registerLoader(new ResourceLocation(MyTutorial.MODID, "fancyloader"), new FancyModelLoader());
//        MagicRenderer.register();
//        MinecraftForge.EVENT_BUS.addListener(InWorldRenderer::render);
//        MinecraftForge.EVENT_BUS.addListener(AfterLivingRenderer::render);
//        RenderTypeLookup.setRenderLayer(Registration.COMPLEX_MULTIPART.get(), RenderType.translucent());

    }

    @SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event) {
//        event.getItemColors().register((stack, i) -> 0xff0000, Registration.WEIRDMOB_EGG.get());
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
//        if (!event.getMap().getBasePath().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
//            return;
//        }
//
//        event.addSprite(MAGICBLOCK_TEXTURE);
    }
}
