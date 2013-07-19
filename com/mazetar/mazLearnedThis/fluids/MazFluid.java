package com.mazetar.mazLearnedThis.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class MazFluid extends Fluid {

    public MazFluid() {
        super("MaziumFluid");
        
        setDensity( 3 ); // used by the block to work out how much it slows entities
        setViscosity( 4000 ); // used by the block to work out how fast it flows
        setLuminosity(5);
        FluidRegistry.registerFluid( this );
        
    }
    
    
}
