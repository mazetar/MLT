package com.mazetar.mazLearnedThis.basemaker;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBuildingPlacer extends Item {

    public ItemBuildingPlacer(int Id) {
        super(Id);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        
    }
    
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        // TODO Auto-generated method stub
        return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
    }
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
            int par6, int par7, float par8, float par9, float par10) {
        // TODO Auto-generated method stub
        return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5,
                par6, par7, par8, par9, par10);
    }
    
    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        // TODO Auto-generated method stub
        return super.onEntityItemUpdate(entityItem);
    }
    
    //Called each tick as long the item is on a player inventory. 
    //Uses by maps to check if is on a player hand and update it's contents.
    @Override
    public void onUpdate(ItemStack item, World w,
            Entity e, int invSlotNum, boolean isSelected) {

        if (isSelected)
        {
            callToDrawTempBuilding();
        }
    }
    
    @SideOnly(Side.CLIENT)
    void callToDrawTempBuilding()
    {
        
    }
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        // TODO Auto-generated method stub
        super.registerIcons(par1IconRegister);
    }

}
