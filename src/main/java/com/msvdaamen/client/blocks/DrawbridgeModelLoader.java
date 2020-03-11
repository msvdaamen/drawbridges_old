package com.msvdaamen.client.blocks;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class DrawbridgeModelLoader implements IModelLoader<DrawbridgeModelGeometry> {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public DrawbridgeModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new DrawbridgeModelGeometry();
    }
}
