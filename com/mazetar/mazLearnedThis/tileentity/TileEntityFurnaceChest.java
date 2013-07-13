package com.mazetar.mazLearnedThis.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityFurnaceChest extends TileEntityMaz implements IInventory {

    private String inventoryName = "FurnaceChest";
    private ItemStack[] furnaceChestContents = new ItemStack[54];
    private int slotCurrentlySmeltingFrom = -1;

    /** The number of ticks that the furnace will keep burning */
    public int furnaceBurnTime = 0;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int currentItemBurnTime = 0;

    /** The number of ticks that the current item has been cooking for */
    public int furnaceCookTime = 0;
    private String name = "TileEntity.FurnaceChest";

    
    /*
     * Called when the block needs to update it's ON/OFF State.
     */
    private void updateBlockIsActive() {
        // TODO Auto-generated method stub
        
    }
    
    
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 54;
    }

   
    
    
    

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return inventoryName;
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return true;
    }

    public void func_94129_a(String par1Str)
    {
        this.inventoryName = par1Str;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.furnaceChestContents = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceChestContents.length)
            {
                this.furnaceChestContents[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceChestContents[1]);

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.name = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceChestContents.length; ++i)
        {
            if (this.furnaceChestContents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceChestContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.name);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return this.furnaceCookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the furnace is currently burning
     */
    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean isBurning = this.furnaceBurnTime > 0;
        boolean isSmeltingItem = false;
        int slotWithFuel = -1;

        if (this.furnaceBurnTime > 0)
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            slotWithFuel = getSlotWithFuelItem();
            if (slotCurrentlySmeltingFrom == -1)
                slotCurrentlySmeltingFrom = getSlotWithSmeltableItem();
            
            if (this.furnaceBurnTime == 0 && slotCurrentlySmeltingFrom != -1 && slotWithFuel != -1)
            {
                
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceChestContents[slotWithFuel]);

                if (this.furnaceBurnTime > 0)
                {
                    isSmeltingItem = true;

                    if (this.furnaceChestContents[slotWithFuel] != null)
                    {
                        --this.furnaceChestContents[slotWithFuel].stackSize;

                        if (this.furnaceChestContents[slotWithFuel].stackSize == 0)
                        {
                            this.furnaceChestContents[slotWithFuel] = this.furnaceChestContents[slotWithFuel].getItem().getContainerItemStack(furnaceChestContents[slotWithFuel]);
                        }
                    }
                }
            }

            if (this.isBurning() && slotCurrentlySmeltingFrom != -1)
            {
                ++this.furnaceCookTime;

                if (this.furnaceCookTime == 200)
                {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    isSmeltingItem = true;
                }
            }
            else
            {
                this.furnaceCookTime = 0;
            }

            if (isBurning != this.furnaceBurnTime > 0)
            {
                isSmeltingItem = true;
                this.updateBlockIsActive();
            }
        }

        if (isSmeltingItem)
        {
            this.onInventoryChanged();
        }
    }
    
    
    private int getSlotWithFuelItem(){
        for (int i = 0; i < furnaceChestContents.length; i++) {
            if (getItemBurnTime( furnaceChestContents[i]) > 0)
                return i;
        }
        return -1;
    }

    private int getSlotWithSmeltableItem(){
        for (int i = 0; i < furnaceChestContents.length; i++) {
            ItemStack item = furnaceChestContents[i];
            if (canSmeltItem(item))
                return i;
        }
        return -1;
    }
    
    private boolean canSmeltItem(ItemStack item){
        ItemStack smeltingResult = FurnaceRecipes.smelting().getSmeltingResult(item);
        if (smeltingResult != null && item != null)
            return true;
        return false;
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (slotCurrentlySmeltingFrom != -1)
        {
            ItemStack outputItem = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceChestContents[slotCurrentlySmeltingFrom]);
            
            // Get empty slot or slot Containing same item, then try to place that item inn the slot.
            // returns true if it's possible to place item.
            if (placeSmeltedItemIntoChest(outputItem)){
                --this.furnaceChestContents[slotCurrentlySmeltingFrom].stackSize;

                if (this.furnaceChestContents[slotCurrentlySmeltingFrom].stackSize <= 0)
                {
                    this.furnaceChestContents[slotCurrentlySmeltingFrom] = null;
                }
                slotCurrentlySmeltingFrom = -1; // Done smelting current slot
            }
            
            
            

            
        }
    }
    
    private boolean placeSmeltedItemIntoChest(ItemStack outputItem){
        
        
        // Try place into free open slot or similar item stack.
        for (int i = 0; i < furnaceChestContents.length; i++) {
            if (furnaceChestContents[i] == null)
            {
                furnaceChestContents[i] = outputItem.copy();
            }
            else if (furnaceChestContents[i].isItemEqual(outputItem))
            {
                if (furnaceChestContents[i].stackSize + outputItem.stackSize <= furnaceChestContents[i].getMaxStackSize())
                furnaceChestContents[i].stackSize += outputItem.stackSize;
                else
                {
                    outputItem.stackSize = (furnaceChestContents[i].stackSize + outputItem.stackSize) - furnaceChestContents[i].getMaxStackSize();
                    furnaceChestContents[i].stackSize = furnaceChestContents[i].getMaxStackSize();
                    
                
                }
            }
        }
        
        
        
        return false;
    }
    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int i = par0ItemStack.getItem().itemID;
            Item item = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null)
            {
                Block block = Block.blocksList[i];

                if (block == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (block.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) return 200;
            if (i == Item.stick.itemID) return 100;
            if (i == Item.coal.itemID) return 1600;
            if (i == Item.bucketLava.itemID) return 20000;
            if (i == Block.sapling.blockID) return 100;
            if (i == Item.blazeRod.itemID) return 2400;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}


    
    



    @Override
    public ItemStack getStackInSlot(int slot) {
            return furnaceChestContents[slot];
    }


    @Override
    public ItemStack decrStackSize(int slot, int amt) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    if (stack.stackSize <= amt) {
                            setInventorySlotContents(slot, null);
                    } else {
                            stack = stack.splitStack(amt);
                            if (stack.stackSize == 0) {
                                    setInventorySlotContents(slot, null);
                            }
                    }
            }
            return stack;
    }


    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    setInventorySlotContents(slot, null);
            }
            return stack;
    }


    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        furnaceChestContents[slot] = stack;
            if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                    stack.stackSize = getInventoryStackLimit();
            }               
    }



    


    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return false;
    }


}
