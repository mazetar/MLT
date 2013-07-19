package com.mazetar.mazLearnedThis.item;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

import com.mazetar.mazLearnedThis.fluids.ItemMazFluidBucket;
import com.mazetar.mazLearnedThis.fluids.ModFluids;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItems {

    // Declearing Items
    
    // Item item;
    public static ItemPortalIgniter portalIgniter;
   // public static ItemMazFluidContainer mazFluidContainer;
    
    public static ItemMazFluidBucket bucketMazFluid;
    
    public static void init(){
        // Initialize Item here
        
        //item = new Item();
        portalIgniter = new ItemPortalIgniter(2000);
        bucketMazFluid = new ItemMazFluidBucket(2001, 10);
        
        //GameRegistry.registerItem(item, ItemName);
        GameRegistry.registerItem(portalIgniter, "Portal Igniter");
        GameRegistry.registerItem(bucketMazFluid, "Bucket MazFluid");
        
        LanguageRegistry.addName(bucketMazFluid, "Bucket of Mazium Fluid");
        
        registerContainers();
        
       initializeRecipes();
    }

    static void registerContainers()
    {
        FluidContainerRegistry.registerFluidContainer(
                new FluidContainerData(
                    FluidRegistry.getFluidStack( ModFluids.mazFluid.getName(), FluidContainerRegistry.BUCKET_VOLUME ),
                    new ItemStack( bucketMazFluid ),
                    new ItemStack( Item.bucketEmpty )
                )
            );
    }
    
    private static void initializeRecipes() {
        
    }
}
