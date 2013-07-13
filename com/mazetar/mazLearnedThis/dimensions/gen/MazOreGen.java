package com.mazetar.mazLearnedThis.dimensions.gen;

import java.util.Random;

import com.mazetar.mazLearnedThis.dimensions.gen.features.WorldGenStoneHouse;
import com.mazetar.mazLearnedThis.lib.DimensionRef;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class MazOreGen implements IWorldGenerator {

    public static int rarity = 50;

    /** Methods For Ore Generation **/
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 0)// surface
        {
            this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
        } else if (world.provider.dimensionId == -1)// End
        {
            this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
        } else if (world.provider.dimensionId == 1)// Nether
        {
            generateNether(world, random, chunkX * 16, chunkZ * 16,
                    chunkProvider, chunkX, chunkZ);
        } else if (world.provider.dimensionId == DimensionRef.MAZ_DIMENSION_ID)//
        {
            this.generateMazDimension(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    /** Generate's the Ore's In Surface World **/
    private void generateSurface(World world, Random random, int chunkX,
            int chunkZ) {
        // the 4 below is for how rare it will be
        for (int i = 0; i < 4; i++) {
            // this below just tells me if its generating or not
            System.out.println("Generating Emerald in overworld");
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(64);// the 128 is the max height the
                                            // ore/block will generate
            int zCoord = chunkZ + random.nextInt(16);
            // The 230 on the line below is how meny will generate per vain, as
            // an example i think diamond is like 2 or 4
            // and the Block.blockEmerald is what it will spawn
            (new WorldGenMinable(Block.blockEmerald.blockID, 20)).generate(
                    world, random, xCoord, yCoord, zCoord);
        }
    }

    /** Generates Ore's In Nether **/
    private void generateNether(World world, Random random, int chunkX,
            int chunkZ, IChunkProvider par1IChunkProvider, int par2, int par3) {
        // the 4 below is for how rare it will be
        for (int i = 0; i < 4; i++) {
            // this below just tells me if its generating or not
            System.out.println("Generating Gold in the End");
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(256);// the 128 is the max height the
                                             // ore/block will generate
            int zCoord = chunkZ + random.nextInt(16);
            // The 230 on the line below is how meny will generate per vain, as
            // an example i think diamond is like 2 or 4
            // and the Block.blockGold is what it will spawn
            (new WorldGenMinableNether(Block.blockGold.blockID, 20)).generate(
                    world, random, xCoord, yCoord, zCoord);
            
            
        }
    }

    /** Generates Ore's in TheEnd **/
    private void generateEnd(World world, Random random, int chunkX, int chunkZ) {
        // the 4 below is for how rare it will be
        for (int i = 0; i < 4; i++) {
            // this below just tells me if its generating or not
            System.out.println("Generating Diamond in the End");
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(64);// the 128 is the max height the
                                            // ore/block will generate
            int zCoord = chunkZ + random.nextInt(16);
            // The 230 on the line below is how meny will generate per vain, as
            // an example i think diamond is like 2 or 4
            // and the Block.blockDiamond is what it will spawn
            (new WorldGenMinableEnd(Block.blockDiamond.blockID, 20)).generate(
                    world, random, xCoord, yCoord, zCoord);
        }
    }

    private void generateMazDimension(World world, Random random, int chunkX,
            int chunkZ) {
        // the 4 below is for how rare it will be
        for (int i = 0; i < 4; i++) {
            // this below just tells me if its generating or not
            System.out.println("Generating GlowStone in the Maz dimension");
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(128);// the 128 is the max height the
                                             // ore/block will generate
            int zCoord = chunkZ + random.nextInt(16);
            // The 20 on the line below is how meny will generate per vain, as
            // an example i think diamond is like 2 or 4
            // and the Block.blockIron is what it will spawn
            (new WorldGenMinableMaz(Block.glowStone.blockID, 20))
                    .generate(world, random, xCoord, yCoord, zCoord);
        }
        for (int k = 0; k < rarity; k++) // Creates a new integer, 'k', which is
                                         // between 0 and whatever we used for
                                         // the rarity.
        {
            // this below just tells me if its generating or not
            System.out.println("Generating House in overworld");

            int RandPosX = chunkX + random.nextInt(16); // These states the
                                                        // position of the
                                                        // structure; between 0
                                                        // and 16 for the x and
                                                        // z axes, and between 0
                                                        // and 128 (just below
                                                        // cloud level for the
            int RandPosY = random.nextInt(128); // y axis. This is very standard
                                                // stuff, so you don't need to
                                                // change any of this except the
                                                // rarity. You can play around
                                                // with these figures
            int RandPosZ = chunkZ + random.nextInt(16); // if you want, but
                                                        // nothing really needs
                                                        // to be altered.

            (new WorldGenStoneHouse()).generate(world, random, RandPosX, RandPosY, RandPosZ);
        }
    }

}
