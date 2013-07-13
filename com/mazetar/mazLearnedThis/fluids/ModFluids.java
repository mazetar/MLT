package com.mazetar.mazLearnedThis.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {

    
    public static Fluid fluidMaz;

    public static void init(){
        
        fluidMaz = new MazFluid("MaziumFluid");
        
        FluidRegistry.registerFluid(fluidMaz);
        
        
        
        
    }
}
