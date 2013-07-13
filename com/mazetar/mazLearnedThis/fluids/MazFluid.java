package com.mazetar.mazLearnedThis.fluids;

import com.mazetar.mazLearnedThis.block.ModBlocks;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class MazFluid extends Fluid {

    public MazFluid(String fluidName) {
        super(fluidName);
        setBlockID(ModBlocks.BlockID_BlockClassicFluid);
        setLuminosity(5);
    }
    
    @Override
    public int getColor() {
        // TODO Auto-generated method stub
        return 0xCCFF00;
    }
    
    
    

}
