package com.mazetar.mazLearnedThis.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

public class TileEntityTest extends TileEntity {

    static int Eblock = Block.blockEmerald.blockID;
    static int Dblock = Block.blockDiamond.blockID;
    static int Gblock = Block.blockGold.blockID;

    static int[][][] building = 
            { 
        { { Eblock, Eblock, Eblock, Eblock, Eblock, Eblock },
          { Dblock, Dblock, Dblock, Dblock, Dblock },
          { Gblock, Gblock, Gblock, Gblock, Gblock, Gblock, Gblock } } };

    boolean isBuilding = false;
    String name = "test Building";

    int UpdateFrequency = 40;
    int currentTickCount = 0;

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        if (isBuilding) {
            currentTickCount++;
            if (currentTickCount >= UpdateFrequency) {
                currentTickCount = 0;
                build();
            }
        }

    }

    int LoopX = 0;
    int LoopY = 0;
    int LoopZ = 0;
    int MaxBlocks = 6;
    int[] teLoc = {0,0,0};
    
    private void build() {
        int blockCount = 0;
        int startX = this.xCoord;
        int startY = this.yCoord;
        int startZ = this.zCoord;
        int zeroX = LoopX;
        int zeroY = LoopY;
        int zeroZ = LoopZ;
        
        for (int x = zeroX; x < building.length; x++) {
            zeroX = 0;
            for (int y = zeroY; y < building[x].length; y++) {
                zeroY = 0;
                for (int z = zeroZ; z < building[x][y].length; z++) {
                    zeroZ = 0;
                    blockCount++;
                    // Build here;
                    int currentBlock = building[x][y][z];
                    if (startX + x == this.xCoord && startY + y == this.yCoord
                            && startZ + z == this.zCoord) {
                        // Skip this block beacuse it's the last block one should place!
                        teLoc[0] = x;
                        teLoc[1] = y;
                        teLoc[2] = z;

                    } else
                        this.worldObj.setBlock(startX + x, startY + y, startZ
                                + z, currentBlock);
                    //

                    if (blockCount >= MaxBlocks) {
                        LoopX = x;
                        LoopY = y;
                        LoopZ = z;
                        return;
                    }
                }
            }
        } // End X loop

        // Since all blocks are placed, we are done building!
        this.isBuilding = false;
        this.worldObj.setBlock(xCoord, yCoord, zCoord, building[teLoc[0]][teLoc[1]][teLoc[2]]);

    }

    public void onBlockActivated(World w, int x, int y, int z, EntityPlayer p,
            int par6, float par7, float par8, float par9) {

        if (!isBuilding) {
            isBuilding = true;
            ChatMessageComponent msg = new ChatMessageComponent();
            msg.func_111079_a("Starting Construction of " + name);
            p.sendChatToPlayer(msg);
        }

    }

}
