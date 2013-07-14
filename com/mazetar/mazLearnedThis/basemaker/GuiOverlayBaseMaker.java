package com.mazetar.mazLearnedThis.basemaker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class GuiOverlayBaseMaker extends Gui {
    
    
    
    /*
     * Draws the TopBar for the basemaker.
     */
    public void DrawTopBar(RenderGameOverlayEvent event, Minecraft mc) {
        // Top bar is devided into 3 areas | POWER | DIV | RESOURCES |
        
    int posX = (mc.displayWidth / 10) * 2;
    int posY = 0;
    int height = mc.displayHeight / 20;
     // drawRect x0, y0, x1, y1, color
    drawRect(10, 10, 100, 100, 0x383838);
    
    
        
        
        
    }
    
}
