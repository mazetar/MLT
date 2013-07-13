package com.mazetar.mazLearnedThis.client.gui;

import org.lwjgl.opengl.GL11;

public class DrawingUtil {

    
    public static void DrawCubeLines(float x, float y, float z, float x1, float y1, float z1)
    {
        GL11.glLineWidth(10F);
        GL11.glBegin(GL11.GL_LINES);
        float offset = 0.55F;
        // TOP
        GL11.glVertex3f(x+offset,y,z+offset);
        GL11.glVertex3f(x+offset,y,z1-offset);
        
        GL11.glVertex3f(x+offset,y,z+offset);
        GL11.glVertex3f(x1-offset,y,z-offset);
        
        GL11.glVertex3f(x+offset,y,z1-offset);
        GL11.glVertex3f(x1-offset,y,z1-offset);
        
        GL11.glVertex3f(x1-offset,y,z1-offset);
        GL11.glVertex3f(x1-offset,y,z+offset);

        // BOTTOM
        GL11.glVertex3f(x+offset,y1,z+offset);
        GL11.glVertex3f(x+offset,y1,z1-offset);
        
        GL11.glVertex3f(x+offset,y1,z+offset);
        GL11.glVertex3f(x1-offset,y1,z-offset);
        
        GL11.glVertex3f(x+offset,y1,z1-offset);
        GL11.glVertex3f(x1-offset,y1,z1-offset);
        
        GL11.glVertex3f(x1-offset,y1,z1-offset);
        GL11.glVertex3f(x1-offset,y1,z-offset);
        
        // SIDES
        GL11.glVertex3f(x+offset,y1,z+offset);
        GL11.glVertex3f(x+offset,y,z+offset);
        
        GL11.glVertex3f(x+offset,y1,z1-offset);
        GL11.glVertex3f(x+offset,y,z1-offset);

        GL11.glVertex3f(x1-offset,y1,z1-offset);
        GL11.glVertex3f(x1-offset,y,z1-offset);
        
        GL11.glVertex3f(x1-offset,y1,z+offset);
        GL11.glVertex3f(x1-offset,y,z+offset);
        
        
        
        GL11.glEnd();
    }
}
