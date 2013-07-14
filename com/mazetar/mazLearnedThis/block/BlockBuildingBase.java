package com.mazetar.mazLearnedThis.block;

import com.mazetar.mazLearnedThis.tileentity.TileEntityObjBuilding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBuildingBase extends Block {

    public BlockBuildingBase(int id) {
        super(id, Material.iron);
    }
    
    @Override
    public boolean onBlockActivated(World w, int x, int y,
            int z, EntityPlayer p, int par6, float par7,
            float par8, float par9) {
        TileEntity te = w.getBlockTileEntity(x, y, z);
        if (te instanceof TileEntityObjBuilding)
        {
            ((TileEntityObjBuilding) te).onActivated(p, par6, par7, par8, par9);
        }
        
        return true;
    }

}
