package com.mazetar.mazLearnedThis.client.model;

import org.lwjgl.opengl.GL11;

import com.mazetar.mazLearnedThis.lib.Reference;
import com.mazetar.mazLearnedThis.tileentity.TileEntityObjBuilding;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelBaseBuildings extends ModelMazBase {
    private IModelCustom modelTutorial;
    
    public ModelBaseBuildings()
    {
        modelTutorial = AdvancedModelLoader.loadModel("/assets/"+ Reference.MOD_ID +"/models/turner.obj");
    }
    
    public void render()
    {
        modelTutorial.renderAll();
    }
    
    public void render(TileEntityObjBuilding tileEntity, double x, double y, double z)
    {
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();
      //  GL11.glDisable(GL11.GL_LIGHTING);
        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.65f, (float)z + 0.5f);
     
        // Scale our object to about half-size in all directions (the OBJ file is a little large)
        GL11.glScalef(0.5f, 0.5f, 0.5f);
     
        // Bind the texture, so that OpenGL properly textures our block.
        FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation(Reference.MOD_ID, "\\textures\\models\\turner.png"));
        
        // Render the object, using modelTutBox.renderAll();
        this.render();

        // Re-enable Lighting Calculations
      //  GL11.glEnable(GL11.GL_LIGHTING);
        
        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }
    
}
