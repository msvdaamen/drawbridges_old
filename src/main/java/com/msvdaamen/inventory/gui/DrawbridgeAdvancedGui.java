package com.msvdaamen.inventory.gui;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.inventory.container.DrawbridgeAdvancedContainer;
import com.msvdaamen.tileentities.DrawbridgeAdvancedTileEntity;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class DrawbridgeAdvancedGui extends GuiContainer {

    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;
    private DrawbridgeAdvancedTileEntity te;
    private static final ResourceLocation background = new ResourceLocation(Drawbridges.MODID, "textures/gui/drawbridge_advanced.png");

    public DrawbridgeAdvancedGui(DrawbridgeAdvancedTileEntity tileEntity, DrawbridgeAdvancedContainer container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
        this.te = tileEntity;
    }

    @Override
    public void initGui() {
        super.initGui();
        String name = "Redstone mode";
//        if(te.getMode()) {
//            name = "Redstone mode";
//        }else {
//            name = "Pulse mode";
//        }
        GuiButton button = new GuiButton(1, (guiLeft + WIDTH / 2) - 10, guiTop + 17, name);
        button.setWidth(90);
        addButton(button);
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
//        String s = "Extended Drawbridge";
//        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
//    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 1) {
//            if(te.getMode()) {
//                te.setMode(false);
//                button.displayString = "Pulse mode";
//                PacketHandler.INSTANCE.sendToServer(new PacketModeToggle(false, te.getPos()));
//            }else {
//                te.setMode(true);
//                button.displayString = "Redstone mode";
//                PacketHandler.INSTANCE.sendToServer(new PacketModeToggle(true, te.getPos()));
//            }
        }
    }
}
