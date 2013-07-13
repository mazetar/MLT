package com.mazetar.mazLearnedThis.biomes;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenWhite extends BiomeGenBase {

    public BiomeGenWhite(int biomeID) {
        super(biomeID);
        
        this.biomeName = "White Biome";
        this.topBlock = (byte)Block.blockSnow.blockID;
        this.fillerBlock = (byte)Block.blockSnow.blockID;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.waterColorMultiplier = 0x000000;
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySnowman.class, 60, 1, 5));
    }
    
    public float getSpawningChance()
    {
        return 0.3F;
    }
    
}
