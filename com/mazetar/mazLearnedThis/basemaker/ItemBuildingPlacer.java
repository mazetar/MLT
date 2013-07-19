package com.mazetar.mazLearnedThis.basemaker;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
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
        return super.onItemRightClick(par1ItemStack, par2World,
                par3EntityPlayer);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
            int par6, int par7, float par8, float par9, float par10) {
        // TODO Auto-generated method stub
        return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World,
                par4, par5, par6, par7, par8, par9, par10);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        // TODO Auto-generated method stub
        return super.onEntityItemUpdate(entityItem);
    }

    // Called each tick as long the item is on a player inventory.
    // Uses by maps to check if is on a player hand and update it's contents.
    @Override
    public void onUpdate(ItemStack item, World w, Entity e, int invSlotNum,
            boolean isSelected) {

        if (isSelected) {
            callToDrawTempBuilding();
        }
    }

    @SideOnly(Side.CLIENT)
    void callToDrawTempBuilding() {
        // Get the block position at target.
        // +1y to that gets the position to draw at.
        Minecraft mc = ModLoader.getMinecraftInstance();

        MovingObjectPosition mop = mc.renderViewEntity.rayTrace(100, 0.5F);
        int x = 0;
        int y = 0;
        int z = 0;
        if (mop != null) {
            if (mop.entityHit != null) {
                x = mop.entityHit.serverPosX;
                y = mop.entityHit.serverPosY;
                z = mop.entityHit.serverPosZ;
            } else {
                x = mop.blockX;
                y = mop.blockY + 1; // gets the block above the current one.
                z = mop.blockZ;
            }
            
            // mop != null - positions are set.
            // If there is empty space
            if (mc.theWorld.getBlockId(x, y, z) == 0)
            {
                
            }
            
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        // TODO Auto-generated method stub
        super.registerIcons(par1IconRegister);
    }

}
