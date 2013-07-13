package com.mazetar.mazLearnedThis.item;


import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    // Declearing Items
    
    // Item item;
    public static ItemPortalIgniter portalIgniter;
   // public static ItemMazFluidContainer mazFluidContainer;
    
    public static void init(){
        // Initialize Item here
        
        //item = new Item();
        portalIgniter = new ItemPortalIgniter(2000);
     //   mazFluidContainer = new ItemMazFluidContainer(2001, 10);
        
        //GameRegistry.registerItem(item, ItemName);
        GameRegistry.registerItem(portalIgniter, "Portal Igniter");
    //    GameRegistry.registerItem(mazFluidContainer, "Mazium Container");
        
        
       initializeRecipes();
    }

    private static void initializeRecipes() {
        
        
        
        
    }
}
