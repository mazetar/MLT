package com.mazetar.mazLearnedThis.biomes;

import com.mazetar.mazLearnedThis.lib.BiomeRef;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.world.biome.BiomeGenBase;

public class ModBiomes {
    
    public static BiomeGenBase mazBiome;
    public static BiomeGenBase darkBiome;
    public static BiomeGenBase whiteBiome;
    
    
    public static void init(){
        
        mazBiome = new BiomeGenMaz(BiomeRef.MAZ_BIOME_ID);
        darkBiome = new BiomeGenDark(BiomeRef.MAZ_BIOME_ID + 1);
        whiteBiome = new BiomeGenWhite(BiomeRef.MAZ_BIOME_ID + 2);
        
        GameRegistry.addBiome(darkBiome);
        GameRegistry.addBiome(mazBiome);
        GameRegistry.addBiome(whiteBiome);
        }
    
    
}
