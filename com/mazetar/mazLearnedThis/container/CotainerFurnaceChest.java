package com.mazetar.mazLearnedThis.container;


import com.mazetar.mazLearnedThis.tileentity.TileEntityFurnaceChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class CotainerFurnaceChest extends Container {

        public TileEntityFurnaceChest tileEntity;
        public InventoryPlayer playerInventory;
        
    
    public CotainerFurnaceChest(TileEntity te,
            EntityPlayer player) {
        tileEntity = (TileEntityFurnaceChest)te;
        playerInventory = player.inventory;
        int rowCount = 6;
        int colCount = 9;
        final int SLOT_SIZE = 18;
        final int offsetX = 14;
        final int offsetY = 0;
        final int playerInvY = 14 + rowCount*SLOT_SIZE + offsetY;
        final int playerHotbarY = playerInvY + SLOT_SIZE * 3 + 4;

        
        
      //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        /* Sets up the chest inventory */
        int slotNum = 0;
        int x = 0;
        int y = 0;
        
        for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    x = offsetX + col * SLOT_SIZE;
                    y = offsetY + row * SLOT_SIZE;
                        addSlotToContainer(new Slot(tileEntity, slotNum, x, y));
                        slotNum++;
                     // New Slot (Inventory, SlotNumber, xPosition, yPosition);
                        
                }
        }
        
        /* Player Inventory */
        for (int playerRow = 0; playerRow < 3; playerRow++){
            for (int playerCol = 0; playerCol < 9; playerCol++){
                x = offsetX + playerCol * SLOT_SIZE;
                y = playerInvY + playerRow * SLOT_SIZE;
               addSlotToContainer(new Slot(playerInventory, playerCol + playerRow * 9 + 9, x, y));
            }
        }
        /* Player hotbar */
        for (int hotSlotNum = 0; hotSlotNum < 9; hotSlotNum++){
            x = offsetX + hotSlotNum * SLOT_SIZE;
            y = playerHotbarY;
               addSlotToContainer(new Slot(playerInventory, hotSlotNum, x, y));
        }
        
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(i);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            int invSize = tileEntity.getSizeInventory();
            if (i < invSize)
            {
                if (!mergeItemStack(itemstack1, invSize, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 0, invSize, false))
            {
                return null;
            }
            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
    

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
        return true;
    }

}
