package com.mazetar.mazLearnedThis.block;

import com.mazetar.mazLearnedThis.lib.Reference;
import com.mazetar.mazLearnedThis.tileentity.TileEntityTest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBuildingConstructor extends Block {

    
    
    public BlockBuildingConstructor(int id, String name) {
        super(id, Material.iron);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setUnlocalizedName(name);
    }
    
    
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    
    @Override
    public void onBlockAdded(World w, int x, int y, int z) {
        super.onBlockAdded(w, x, y, z);
        w.setBlockTileEntity(x, y, z, new TileEntityTest());
    }
    
    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
            EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        
        super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving,
                par6ItemStack);
        if (par1World.getBlockTileEntity(par2, par3, par4) == null)
            par1World.setBlockTileEntity(par2, par3, par4, new TileEntityTest());
    }
    
    @Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4,
            int par5, float par6, float par7, float par8, int par9) {
        if (par1World.getBlockTileEntity(par2, par3, par4) == null)
            par1World.setBlockTileEntity(par2, par3, par4, new TileEntityTest());
        
        return super.onBlockPlaced(par1World, par2, par3, par4, par5, par6, par7, par8,
                par9);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir) {
        this.blockIcon = ir.registerIcon(Reference.MOD_ID + ":BlockFurnaceChestSideOff");
    }
    
    @Override
    public boolean onBlockActivated(World w, int x, int y,
            int z, EntityPlayer p, int par6, float par7,
            float par8, float par9) {
        
        TileEntityTest te = (TileEntityTest)w.getBlockTileEntity(x, y, z);
        if (te == null)
            return false;
        
        te.onBlockActivated(w, x, y, z, p,
                par6, par7, par8, par9);
        
        return true;
    }
    
    
    
}
