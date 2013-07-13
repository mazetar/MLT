package com.mazetar.mazLearnedThis.client.renderer;

import com.mazetar.mazLearnedThis.client.model.ModelEntityMaz;
import com.mazetar.mazLearnedThis.entity.EntityMaz;
import com.mazetar.mazLearnedThis.lib.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderEntityMaz extends RenderLiving{
    
    protected ModelEntityMaz model;
    
    public RenderEntityMaz(ModelBase modelEntityMaz, float shadowSize) {
        super(modelEntityMaz, shadowSize);
        model = ((ModelEntityMaz)modelEntityMaz);
    }
    
    public void RenderEntityMaz(EntityMaz entity, double x, double y, double z, float par8, float partialTickTime)
    {
        super.doRenderLiving(entity, x, y, z, par8, partialTickTime);
    }
 
 public void doRenderLiving(EntityLiving entity, double x, double y, double z, float par8, float partialTickTime)
    {
        RenderEntityMaz((EntityMaz)entity, x, y, z, par8, partialTickTime);
    }
 
 public void doRender(Entity par1Entity, double x, double y, double z, float par8, float partialTickTime)
    {
        RenderEntityMaz((EntityMaz)par1Entity, x, y, z, par8, partialTickTime);
    }

@Override
protected ResourceLocation func_110775_a(Entity entity) {
    return new ResourceLocation( Reference.MOD_ID, "textures/entity/EntityMaz.png");
}
    
}
