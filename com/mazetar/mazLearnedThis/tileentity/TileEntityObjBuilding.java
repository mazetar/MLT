package com.mazetar.mazLearnedThis.tileentity;

import com.mazetar.mazLearnedThis.basemaker.Building;
import com.mazetar.mazLearnedThis.basemaker.TileEntityBuildingCreator;
import com.mazetar.mazLearnedThis.client.model.ModelMazBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityObjBuilding extends TileEntity {

    public ModelMazBase Model;
    public Building BaseBuilding;
    public TileEntityObjBuilding(TileEntityBuildingCreator oldTe)
    {
        BaseBuilding = oldTe.Building;
        Model = BaseBuilding.Model;
        this.xCoord = oldTe.xCoord;
        this.yCoord = oldTe.yCoord;
        this.zCoord = oldTe.zCoord;
    }
    public void onActivated(EntityPlayer p,
            int par6, float par7, float par8, float par9) {
        
        
        
    }
 
    
}
