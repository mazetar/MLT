package com.mazetar.mazLearnedThis.dimensions;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

import com.mazetar.mazLearnedThis.lib.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderMazMultipleBiomes extends WorldProvider {

    public void registerWorldChunkManager() {
        /** tells Minecraft to use our new WorldChunkManager **/
        this.worldChunkMgr = new WorldChunkMangerMaz(worldObj.getSeed(),
                terrainType);
        this.hasNoSky = false;
    }

    @Override
    /** Dimension Name **/
    public String getDimensionName() {
        return "MazDimension";
    }

    /** Get Provider for dimension **/
    public static WorldProvider getProviderForDimension(int id) {
        return DimensionManager
                .createProviderFor(DimensionRef.MAZ_DIMENSION_ID);
    }

    /** Welcome message **/
    public String getWelcomeMessage() {
        return "Entering the Mazetarian Dimension";
    }

    /** What chunk provider to use **/
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderMazTest(worldObj, worldObj.getSeed(), true);
    }

    /** Can player re-spawn here **/
    public boolean canRespawnHere() {
        return false;
    }

    /** Set user message **/
    // not sure if this works any more ?
    protected synchronized String setUserMessage(String par1Str) {
        return "Building Mazetar Dimension";
    }

    /** Determines the dimension the player will be respawned in **/
    public int getRespawnDimension(EntityPlayerMP player) {
        return this.dimensionId;
    }

    /** Dimension Movement speed **/
    public double getMovementFactor() {
        return 10.0;
    }

}
