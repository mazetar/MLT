package com.mazetar.mazLearnedThis.block;

import com.mazetar.mazLearnedThis.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockBoundsBlock extends Block {

    public BlockBoundsBlock(int id) {
        super(id, Material.iron);
    }
    
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
       this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":BlockBounds");
       
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world,
            int x, int y, int z) {
        return super.getPickBlock(target, world, x, y, z);
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        
        return true;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
    public void onBlockAdded(World w, int x, int y, int z) {
        w.setBlockTileEntity(x, y, z, new TileEntityBounds());
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
            int par4, int par5) {
        return;
    }
    
    
    
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w,
            int x, int y, int z) {
        TileEntity te = w.getBlockTileEntity(x, y, z);
        if (te instanceof TileEntityBounds) {
            return ((TileEntityBounds)te).boundingBox;
            
        }
        return AxisAlignedBB.getBoundingBox(0, 0, 0, 1F, 1F, 1F);
        
    }
}
