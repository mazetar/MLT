package com.mazetar.mazLearnedThis.fluids;

import com.mazetar.mazLearnedThis.MazLearnedThis;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;

public class ItemMazFluidContainer extends ItemFluidContainer {

    public ItemMazFluidContainer(int itemID, int capacity) {
        super(itemID, capacity);
        this.itemIcon = Item.bucketEmpty.getIconFromDamage(0);
        FluidContainerRegistry.registerFluidContainer(new FluidStack(ModFluids.fluidMaz, 1), new ItemStack(this));
        
    }

}
