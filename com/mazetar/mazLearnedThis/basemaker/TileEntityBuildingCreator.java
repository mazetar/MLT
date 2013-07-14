package com.mazetar.mazLearnedThis.basemaker;

import com.mazetar.mazLearnedThis.block.ModBlocks;
import com.mazetar.mazLearnedThis.tileentity.TileEntityObjBuilding;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBuildingCreator extends TileEntity {
    
    boolean isBuilding;
    boolean isWalled;
    int sizeX;
    int sizeY;
    int sizeZ;
    
    // wall variables
    int ticksPerWallElement = 2;
    int wallTickNumber = 0;
    int wallsPerWallTick = 1;
    int wallStartX;
    int wallStartY;
    int wallStartZ;
    int wallBlock;
    
    // Building construction
    int buildingTicksToComplete = 120 * 20; // 2 minute.
    int currentBuildTick = 0;
    
    public Building Building;
    
    
    public TileEntityBuildingCreator() {
        
    }
    
    public void StartBuilding(Building building)
    {
        Building = building;
        isBuilding = true;
    }
    
    
    
    @Override
    public void updateEntity() {
        
        if (isBuilding)
        {
            
            if (!isWalled)
            {
                wallTickNumber++;
                if (wallTickNumber >= ticksPerWallElement)
                {
                    wallTickNumber = 0;
                    buildWall();
                }
            }
            else
            {
                currentBuildTick++;
                if (currentBuildTick >= buildingTicksToComplete)
                    CompleteBuilding();
            }
            
        }
        
    }
    
    public void CompleteBuilding()
    {
        this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.BUILDING_BASE_ID);
        this.worldObj.setBlockTileEntity(this.xCoord, this.yCoord, this.zCoord, new TileEntityObjBuilding(this));
    }
    

    int wallLoopX = 0;
    int wallLoopY = 0;
    int wallLoopZ = 0;
    void buildWall()
    {
        int blockCount = 0;
        int startX = wallStartX;
        int startY = wallStartY;
        int startZ = wallStartZ;
        int zeroX = wallLoopX;
        int zeroY = wallLoopY;
        int zeroZ = wallLoopZ;
        
        for (int x = 0; x < sizeX; x++) {
            zeroX = 0;
            for (int y = 0; y < sizeY; y++) {
                zeroY = 0;
                for (int z = 0; z < sizeZ; z++) {
                    zeroZ = 0;
                    
                    if (x == 0 || x == sizeX)
                    {
                        this.worldObj.setBlock(startX + x, startY + y, startZ + z, wallBlock);
                        blockCount++;
                    }
                    else if (y == sizeY) 
                    {
                        this.worldObj.setBlock(startX + x, startY + y, startZ + z, wallBlock);
                        blockCount++;
                    }
                    else if (z == 0 || z == sizeZ)
                    {
                        this.worldObj.setBlock(startX + x, startY + y, startZ + z, wallBlock);
                        blockCount++;
                    }
                    
                    
                    if (blockCount >= wallsPerWallTick) {
                        wallLoopX = x;
                        wallLoopY = y;
                        wallLoopZ = z;
                        return;
                    }
                    

                }
            }
        } // x-loop
        
        isWalled = true;
    }
    
    
    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
     // TODO Add NBT save/load
        super.writeToNBT(par1nbtTagCompound);
    }
    
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) 
    {
        // TODO Add NBT save/load
    };
    
}
