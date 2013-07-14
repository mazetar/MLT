package com.mazetar.mazLearnedThis.client.renderer;

import com.mazetar.mazLearnedThis.client.model.ModelBaseBuildings;
import com.mazetar.mazLearnedThis.tileentity.TileEntityObjBuilding;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityObjBuildingRenderer extends TileEntitySpecialRenderer { 
    private ModelBaseBuildings modelTutorial = new ModelBaseBuildings();


    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        ((TileEntityObjBuilding)tileEntity).Model.render((TileEntityObjBuilding)tileEntity, x, y, z);
    }
    
    
    

}
