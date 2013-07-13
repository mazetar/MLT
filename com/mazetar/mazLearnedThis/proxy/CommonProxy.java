package com.mazetar.mazLearnedThis.proxy;

import com.mazetar.mazLearnedThis.container.CotainerFurnaceChest;
import com.mazetar.mazLearnedThis.gui.GuiFurnaceChest;
import com.mazetar.mazLearnedThis.lib.GuiIds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

    
    public void registerTileEntities() {
        
    }
    
    public void registerRenderTickHandler() {
        
    }

    public void registerKeyBindingHandler() {
        
    }

    public void registerSoundHandler() {
    }
    
    public void registerRenderThings(){
        
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {

        if (ID == GuiIds.FURNACE_CHEST_ID)
            return new CotainerFurnaceChest(world.getBlockTileEntity(x, y, z), player);
        else
            return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        if (ID == GuiIds.FURNACE_CHEST_ID)
            return new GuiFurnaceChest(getServerGuiElement(ID, player, world, x, y, z),player, world, x, y, z);
        return null;
    }
}
