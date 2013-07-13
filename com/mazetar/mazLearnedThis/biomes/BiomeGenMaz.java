package com.mazetar.mazLearnedThis.biomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenMaz extends BiomeGenBase{

    public BiomeGenMaz(int biomeID) {
        super(biomeID);
        
        this.setBiomeName("Maz Biome");
        this.topBlock = (byte)Block.blockLapis.blockID;
        this.fillerBlock = (byte)Block.dirt.blockID;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        
    }
    
    
    
    
    
}
