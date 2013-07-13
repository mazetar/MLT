package com.mazetar.mazLearnedThis.gui;

import org.lwjgl.opengl.GL11;

import com.mazetar.mazLearnedThis.container.CotainerFurnaceChest;
import com.mazetar.mazLearnedThis.lib.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiFurnaceChest extends GuiContainer {
    
    public GuiFurnaceChest(Object container, EntityPlayer player, World w, int x, int y, int z) {
         super((CotainerFurnaceChest)container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                    int par3) {
            //draw your Gui here, only thing you need to change is the path
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.func_110577_a(new ResourceLocation(Reference.MOD_ID,"/gui/container.png"));
            /*drawTexturedModalRect( Xpos, Ypos, U, V, Width, Height */
            this.drawTexturedModalRect(130, 18+7, 0, 0, this.xSize, this.ySize);
            
    }

}
