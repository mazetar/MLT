package com.mazetar.mazLearnedThis.fluids;

import com.mazetar.mazLearnedThis.item.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileMazFluidHandler extends TileEntity implements IFluidHandler {

    /*
     * Testing Fluid tanks. Use a simple system to store buckets of fluid inside
     * the tank and retrive them using an empty bucket. it can store up to 10
     * buckets.
     */

    public void onBucketUse(EntityPlayer player, World w, double x, double y, double z, int heldItemId) {

        if (heldItemId == Item.bucketEmpty.itemID) {
            if (this.tank.getFluidAmount() >= FluidContainerRegistry.BUCKET_VOLUME) {
                
                
                player.inventory.consumeInventoryItem(heldItemId);
                
                if (this.tank.getFluid().isFluidEqual(new FluidStack(ModFluids.mazFluid, 1)))
                    player.inventory.addItemStackToInventory(new ItemStack(ModItems.bucketMazFluid));
                else if (this.tank.getFluid().isFluidEqual(new FluidStack(FluidRegistry.WATER, 1)))
                    player.inventory.addItemStackToInventory(new ItemStack(Item.bucketWater));
                else if (this.tank.getFluid().isFluidEqual(new FluidStack(FluidRegistry.LAVA, 1)))
                    player.inventory.addItemStackToInventory(new ItemStack(Item.bucketLava));
                
                this.drain(null, FluidContainerRegistry.BUCKET_VOLUME, true);
            }
            
        }
        else if (heldItemId == ModItems.bucketMazFluid.itemID) {
            
            if (this.tank.fill(new FluidStack(
                    ModFluids.mazFluid, FluidContainerRegistry.BUCKET_VOLUME),
                    true) > 0) {
                player.inventory.consumeInventoryItem(heldItemId);
                player.inventory.addItemStackToInventory(new ItemStack(Item.bucketEmpty));
                
            }
        } else if (heldItemId == Item.bucketWater.itemID) {
            if (this.tank.fill(new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME),
                    true) > 0) {
                player.inventory.consumeInventoryItem(heldItemId);
                player.inventory.addItemStackToInventory(new ItemStack(Item.bucketEmpty));
            }
        } else if (heldItemId == Item.bucketLava.itemID) {
            if (this.tank.fill(new FluidStack(
                    FluidRegistry.LAVA, FluidContainerRegistry.BUCKET_VOLUME),
                    true) > 0) {
                player.inventory.consumeInventoryItem(heldItemId);
                player.inventory.addItemStackToInventory(new ItemStack(Item.bucketEmpty));
            }
        }
        
        
        if (!w.isRemote) {
            return;
        }
        String msg = "Tank is empty! 0 / " + this.tank.getCapacity();
        if (this.tank.getFluidAmount() > 0) {
            msg = "Tank contains: " + this.tank.getFluidAmount() + " / " + this.tank.getCapacity() + " units of " + this.tank.getFluid().getFluid().getName();  
        }
        
        player.addChatMessage(msg);
        
    }

    protected FluidTank tank = new FluidTank(
            FluidContainerRegistry.BUCKET_VOLUME * 10);

    @Override
    public void readFromNBT(NBTTagCompound tag) {

        super.readFromNBT(tag);
        tank.writeToNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {

        super.writeToNBT(tag);
        tank.readFromNBT(tag);
    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource,
            boolean doDrain) {

        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        if (fill(from, new FluidStack(fluid, 1), false) > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        if (tank.drain(1, false) != null)
            return true;
        else
            return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        return new FluidTankInfo[] { tank.getInfo() };
    }

}
