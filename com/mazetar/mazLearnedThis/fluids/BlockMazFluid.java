package com.mazetar.mazLearnedThis.fluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockMazFluid extends BlockFluidClassic {

    
    
    public BlockMazFluid(int id) {
        super(id, ModFluids.mazFluid, Material.water);
        ModFluids.mazFluid.setBlockID(id);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    

    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        return Block.waterMoving.getIcon(side, meta);
    }
    
    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return 0x66FF00;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
            int par4, Entity entity) {
        
       // entity.attackEntityFrom(DamageSource.generic, 5);
    }
}
