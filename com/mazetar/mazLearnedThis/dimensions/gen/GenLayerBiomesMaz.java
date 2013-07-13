package com.mazetar.mazLearnedThis.dimensions.gen;

import com.mazetar.mazLearnedThis.biomes.ModBiomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesMaz extends GenLayer {

    protected BiomeGenBase[] allowedBiomes = { ModBiomes.mazBiome,
            ModBiomes.darkBiome, ModBiomes.whiteBiome };

    public GenLayerBiomesMaz(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    public GenLayerBiomesMaz(long seed) {
        super(seed);
    }

    // This seems to be generating the "ints" which decides which biome to generate next
    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);

        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                this.initChunkSeed(dx + x, dz + z);
                dest[(dx + dz * width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
            }
        }
        return dest;
    }
}
