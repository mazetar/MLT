package com.mazetar.mazLearnedThis.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBounds extends TileEntity {
    
    AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(0, 0, 0, 1F, 1F, 1F);
    public TileEntityBounds() {
        
    }
    
    public void setBoundingBox (float x0, float x1, float y0, float y1, float z0, float z1) {
        boundingBox = AxisAlignedBB.getBoundingBox(x0, y0, z0, x1, y1, z1);
    }
    
    public AxisAlignedBB getBoundingBox() {
        return boundingBox;
    }
    
    
}
