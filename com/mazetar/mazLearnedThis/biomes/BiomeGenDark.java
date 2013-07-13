package com.mazetar.mazLearnedThis.biomes;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenDark extends BiomeGenBase{
    public BiomeGenDark(int biomeID) {
        super(biomeID);
        this.biomeName = "Maz Dark Biome";
        this.topBlock = (byte)Block.blockClay.blockID;
        this.fillerBlock = (byte)Block.cobblestone.blockID;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.waterColorMultiplier = 0x000000;
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 1, 1, 5));
        
    }
    
    
    
}
