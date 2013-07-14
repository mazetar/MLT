package com.mazetar.mazLearnedThis.proxy;

import com.mazetar.mazLearnedThis.block.ModBlocks;
import com.mazetar.mazLearnedThis.client.model.ModelEntityMaz;
import com.mazetar.mazLearnedThis.client.renderer.ItemObjBuilderRenderer;
import com.mazetar.mazLearnedThis.client.renderer.RenderEntityMaz;
import com.mazetar.mazLearnedThis.client.renderer.TileEntityObjBuildingRenderer;
import com.mazetar.mazLearnedThis.client.sounds.SoundEvents;
import com.mazetar.mazLearnedThis.entity.EntityMaz;
import com.mazetar.mazLearnedThis.tileentity.TileEntityObjBuilding;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
   
    
    @Override
    public void registerRenderThings() {
        
        RenderingRegistry.registerEntityRenderingHandler(EntityMaz.class, new RenderEntityMaz(new ModelEntityMaz(), 0.3F));
    
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObjBuilding.class, new TileEntityObjBuildingRenderer());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.OBJ_BUILDER_ID, new ItemObjBuilderRenderer());

        
    }
    
    @Override
    public void registerSoundHandler() {

        MinecraftForge.EVENT_BUS.register(new SoundEvents());
    }
    
    
}
