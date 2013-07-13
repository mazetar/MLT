package com.mazetar.mazLearnedThis.client.renderer;

import com.mazetar.mazLearnedThis.client.model.ModelTutorial;
import com.mazetar.mazLearnedThis.tileentity.TileEntityObjBuilder;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityObjBuilderRenderer extends TileEntitySpecialRenderer { 
    private ModelTutorial modelTutorial = new ModelTutorial();


    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        modelTutorial.render((TileEntityObjBuilder)tileEntity, x, y, z);
    }
    
    
    

}
