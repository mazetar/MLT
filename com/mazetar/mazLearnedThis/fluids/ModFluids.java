package com.mazetar.mazLearnedThis.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;



public class ModFluids {

    public static Fluid mazFluid;
    
    public static void Init()
    {
        mazFluid = new MazFluid();
        
    }

    
    
}
