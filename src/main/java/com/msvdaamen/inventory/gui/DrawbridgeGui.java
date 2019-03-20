package com.msvdaamen.inventory.gui;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.inventory.container.DrawbridgeContainer;
import com.msvdaamen.tileentities.DrawbridgeTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class DrawbridgeGui extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    private static final ResourceLocation background = new ResourceLocation(Drawbridges.MODID, "textures/gui/drawbridge.png");

    public DrawbridgeGui(DrawbridgeTileEntity tileEntity, DrawbridgeContainer container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

//    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
//    {
//        String s = "Drawbridge";
//        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
//    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}