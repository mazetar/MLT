package com.mazetar.mazLearnedThis.block;

import java.util.List;

import com.mazetar.mazLearnedThis.tileentity.TileEntityObjBuilder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockObjBuilder extends Block {

    public BlockObjBuilder(int ID) {
        super(ID, Material.iron);
        
        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = Block.furnaceIdle.getIcon(2, 0);
    }
    
    
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityObjBuilder();
    }
    
    @Override
    public int onBlockPlaced(World w, int x, int y, int z,
            int par5, float par6, float par7, float par8, int par9) {
        if (!w.blockHasTileEntity(x, y, z))
            w.setBlockTileEntity(x, y, z, new TileEntityObjBuilder());
        
        TileEntity te = w.getBlockTileEntity(x, y, z);
        if (te instanceof TileEntityObjBuilder) {
            TileEntityObjBuilder teo = (TileEntityObjBuilder)te;
         // Do whatever to setup the TE.   
        }
        
        
        // Place bounds.
//        w.setBlock(x-1, y, z, ModBlocks.blockBounds.blockID);
//        TileEntity teLowerX = w.getBlockTileEntity(x - 1, y, z);
//        w.setBlock(x+1, y, z, ModBlocks.blockBounds.blockID);
//        TileEntity teHigherX = w.getBlockTileEntity(x + 1, y, z);
//        
//        if (teLowerX instanceof TileEntityBounds) {
//            ((TileEntityBounds)teLowerX).setBoundingBox(0, 1, 0, 0.5F, 0, 1);
//        }
//        if (teHigherX instanceof TileEntityBounds) {
//            ((TileEntityBounds)teHigherX).setBoundingBox(0, 1, 0, 0.5F, 0, 1);
//        }
        return par9;
    }
    
    @Override
    public int getRenderType() {
        return -1;
    }
    
}
