package com.msvdaamen.inventory.gui;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.blocks.Drawbridge;
import com.msvdaamen.blocks.DrawbridgeExtended;
import com.msvdaamen.inventory.container.DrawbridgeContainer;
import com.msvdaamen.inventory.container.DrawbridgeExtendedContainer;
import com.msvdaamen.network.PacketHandler;
import com.msvdaamen.network.PacketUpdateBlockstate;
import com.msvdaamen.tileentities.DrawbridgeExtendedTileEntity;
import com.msvdaamen.tileentities.DrawbridgeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class DrawbridgeExtendedGui extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;
    private DrawbridgeExtendedTileEntity te;

    private static final ResourceLocation background = new ResourceLocation(Drawbridges.MODID, "textures/gui/drawbridge.png");

    public DrawbridgeExtendedGui(DrawbridgeExtendedTileEntity tileEntity, DrawbridgeExtendedContainer container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
        this.te = tileEntity;
    }

    @Override
    public void initGui() {
        super.initGui();
        IBlockState state = this.te.getWorld().getBlockState(this.te.getPos());
        String name = "Redstone mode";
        if(state.getValue(DrawbridgeExtended.PULSE_MODE)) {
            name = "Pulse mode";
        }
        GuiButton button = new GuiButton(1, (guiLeft + WIDTH / 2) - 10, (guiTop + (HEIGHT / 3)), name);
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
//        String s = "Drawbridge";
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
            IBlockState state = this.te.getWorld().getBlockState(this.te.getPos());
            IBlockState newState = state.withProperty(DrawbridgeExtended.PULSE_MODE, !state.getValue(DrawbridgeExtended.PULSE_MODE));
            this.te.getWorld().setBlockState(this.te.getPos(), newState);
            if( this.te.getWorld().getBlockState(this.te.getPos()).getValue(DrawbridgeExtended.PULSE_MODE)) {
                button.displayString = "Pulse mode";
            } else {
                button.displayString = "Redstone mode";
            }
            Block block = newState.getBlock();
            PacketHandler.INSTANCE.sendToServer(new PacketUpdateBlockstate(block.getMetaFromState(newState), te.getPos()));
        }
    }

}