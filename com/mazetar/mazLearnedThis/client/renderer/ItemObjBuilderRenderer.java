package com.mazetar.mazLearnedThis.client.renderer;

import org.lwjgl.opengl.GL11;

import com.mazetar.mazLearnedThis.client.model.ModelBaseBuildings;
import com.mazetar.mazLearnedThis.lib.Reference;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemObjBuilderRenderer implements IItemRenderer {

    private ModelBaseBuildings modelTutorial;
    
    public ItemObjBuilderRenderer() {
        modelTutorial = new ModelBaseBuildings();
        
    }
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }
     
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }
     
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case ENTITY:{
                renderTutorial(0f, 0f, 0f, 0.5f);
                return;
            }
             
            case EQUIPPED:{
                renderTutorial(0f, 1f, 1f, 0.5f);
                return;
            }
                 
            case INVENTORY:{
                renderTutorial(0f, 0f, 0f, 0.5f);
                return;
            }
             
            default:return;
        }
    }
    private void renderTutorial(float x, float y, float z, float scale)
    {
        GL11.glPushMatrix();
     
        // Disable Lighting Calculations
        GL11.glDisable(GL11.GL_LIGHTING);
         
        GL11.glTranslatef(x,  y,  z);
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180f, 0f, 1f, 0f);
         
        FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation(Reference.MOD_ID, "/textures/models/TutBox.png"));
         
        modelTutorial.render();
         
        // Re-enable Lighting Calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    


}
