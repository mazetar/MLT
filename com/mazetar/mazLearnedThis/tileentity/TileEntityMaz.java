package com.mazetar.mazLearnedThis.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityMaz extends TileEntity {
    
    private ForgeDirection orientation;
    
    public TileEntityMaz(){
        orientation = ForgeDirection.SOUTH;
    }
    
    public ForgeDirection getOrientation() {

        return orientation;
    }

    public void setOrientation(ForgeDirection orientation) {

        this.orientation = orientation;
    }

    public void setOrientation(int orientation) {

        this.orientation = ForgeDirection.getOrientation(orientation);
    }

}
