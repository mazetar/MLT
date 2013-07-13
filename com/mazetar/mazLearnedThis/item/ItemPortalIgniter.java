package com.mazetar.mazLearnedThis.item;

import com.mazetar.mazLearnedThis.block.ModBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPortalIgniter extends Item {

    public ItemPortalIgniter(int id) {
        super(id);
        this.setUnlocalizedName("Portal ignition");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        // TODO Auto-generated method stub
        super.registerIcons(par1IconRegister);
    }
    
    @Override
    public boolean onItemUse(ItemStack item,
            EntityPlayer player, World w, int x, int y,
            int z, int par7, float par8, float par9, float par10) {
        
        if (!w.isRemote)
        {
            if (w.getBlockId(x, y, z) == ModBlocks.blockMazPortal.frameBlockID)
                {
                    if (ModBlocks.blockMazPortal.tryToCreatePortal(w, x, y+1, z)) {
                      //  player.sendChatToPlayer("Portal Created");
                        return true;
                    }
               //     player.sendChatToPlayer("Invalid Portal Framework");
                    return false;
                }
       //     player.sendChatToPlayer("Invalid Block ID");
        }
        

        return false;
        
    }

}
