package com.mazetar.mazLearnedThis.block;

import com.mazetar.mazLearnedThis.fluids.TileMazFluidHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFluidStorage extends Block {

    public BlockFluidStorage(int ID) {
        super(ID, Material.iron);
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileMazFluidHandler();
    }
    
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    
    @Override
    public boolean onBlockActivated(World w, int x, int y,
            int z, EntityPlayer p, int par6, float par7,
            float par8, float par9) {
        ItemStack heldItem = p.getHeldItem();
        if (heldItem != null)
        {
            TileEntity te = w.getBlockTileEntity(x, y, z);
            if (te != null)
                ((TileMazFluidHandler)te).onBucketUse(p, w, x, y, z, heldItem.itemID);
        }
        return true;
        
    }
    
    @Override
    public void onBlockClicked(World w, int x, int y, int z,
            EntityPlayer p) {


        
    }
    

}
