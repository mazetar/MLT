package com.mazetar.mazLearnedThis.dimensions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.mazetar.mazLearnedThis.block.ModBlocks;
import com.mazetar.mazLearnedThis.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.PortalPosition;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

/*
 * TODO: Refactor names
 * updating to 1.5.2 had me replace the old code with the vanilla code, renaming is needed!
 */
public class TeleporterMaz extends Teleporter {
    private final WorldServer worldServerInstance;
    private final WorldServer worldServer;
    private final Random random;
    private final LongHashMap mazDestinationCoordinateCache = new LongHashMap();
    private final List mazDestinationCoordinateKeys = new ArrayList();
    private final int portalBlockID = ModBlocks.blockMazPortal.blockID;
    private final int frameBlockID = Block.blockIron.blockID;

    public TeleporterMaz(WorldServer ws) {
        super(ws);
        this.worldServer = ws;
        this.worldServerInstance = ws;
        this.random = new Random(ws.getSeed());
    }

    /**
     * Place an entity in a nearby portal, creating one if necessary.
     */
    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        if (this.worldServerInstance.provider.dimensionId != 1)
        {
            if (!this.placeInExistingPortal(par1Entity, par2, par4, par6, par8))
            {
                this.makePortal(par1Entity);
                this.placeInExistingPortal(par1Entity, par2, par4, par6, par8);
            }
        }
        else
        {
            int i = MathHelper.floor_double(par1Entity.posX);
            int j = MathHelper.floor_double(par1Entity.posY) - 1;
            int k = MathHelper.floor_double(par1Entity.posZ);
            byte b0 = 1;
            byte b1 = 0;

            for (int l = -2; l <= 2; ++l)
            {
                for (int i1 = -2; i1 <= 2; ++i1)
                {
                    for (int j1 = -1; j1 < 3; ++j1)
                    {
                        int k1 = i + i1 * b0 + l * b1;
                        int l1 = j + j1;
                        int i2 = k + i1 * b1 - l * b0;
                        boolean flag = j1 < 0;
                        this.worldServerInstance.setBlock(k1, l1, i2, flag ? frameBlockID : 0);
                    }
                }
            }

            par1Entity.setLocationAndAngles((double)i, (double)j, (double)k, par1Entity.rotationYaw, 0.0F);
            par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
        }
    }

    /**
     * Place an entity in a nearby portal which already exists.
     */
    public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float rotationYaw)
    {
        short portalSearchRange = 128;
        double d3 = -1.0D;
        int portalPosX = 0;
        int portalPosY = 0;
        int portalPosZ = 0;
        int entityPosX = MathHelper.floor_double(entity.posX);
        int entityPosZ = MathHelper.floor_double(entity.posZ);
        long chunkCoordPair = ChunkCoordIntPair.chunkXZ2Int(entityPosX, entityPosZ);
        boolean shouldCreateNewPortal = true;
        double loopZMinusEntityZ;
        int loopX;

        if (this.mazDestinationCoordinateCache.containsItem(chunkCoordPair))
        {
            PortalPosition portalposition = (PortalPosition)this.mazDestinationCoordinateCache.getValueByKey(chunkCoordPair);
            d3 = 0.0D;
            portalPosX = portalposition.posX;
            portalPosY = portalposition.posY;
            portalPosZ = portalposition.posZ;
            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
            shouldCreateNewPortal = false;
        }
        else
        {
            for (loopX = entityPosX - portalSearchRange; loopX <= entityPosX + portalSearchRange; ++loopX)
            {
                double loopXMinusEntityX = (double)loopX + 0.5D - entity.posX;

                for (int loopZ = entityPosZ - portalSearchRange; loopZ <= entityPosZ + portalSearchRange; ++loopZ)
                {
                    double loopYMinusEntityZ = (double)loopZ + 0.5D - entity.posZ;

                    for (int loopY = this.worldServerInstance.getActualHeight() - 1; loopY >= 0; --loopY)
                    {
                        // If it finds a portalBlock inside the search area
                        if (this.worldServerInstance.getBlockId(loopX, loopY, loopZ) == portalBlockID)
                        {
                            // Reduce loopY until it reaches the bottom portalBlock
                            while (this.worldServerInstance.getBlockId(loopX, loopY - 1, loopZ) == portalBlockID)
                            {
                                --loopY;
                            }
                            // BottomOfPortal - EntityPosition = distance between portal and entity?
                            loopZMinusEntityZ = (double)loopY + 0.5D - entity.posY;
                            double loopsMinusEntityCoordsSqr = loopXMinusEntityX * loopXMinusEntityX + loopZMinusEntityZ * loopZMinusEntityZ + loopYMinusEntityZ * loopYMinusEntityZ;

                            if (d3 < 0.0D || loopsMinusEntityCoordsSqr < d3)
                            {
                                d3 = loopsMinusEntityCoordsSqr;
                                portalPosX = loopX;
                                portalPosY = loopY;
                                portalPosZ = loopZ;
                            }
                        }
                    }
                }
            }
        }

        if (d3 >= 0.0D) // If variable has been changed at all (It was -1.0 could be set only to >=0 so naturaly if it's changed it will be true here)
        {
            if (shouldCreateNewPortal)
            {
                this.mazDestinationCoordinateCache.add(chunkCoordPair, new PortalPosition(this, portalPosX, portalPosY, portalPosZ, this.worldServerInstance.getTotalWorldTime()));
                this.mazDestinationCoordinateKeys.add(Long.valueOf(chunkCoordPair));
            }

            double portalWithOffsetX = (double)portalPosX + 0.5D;
            double portalWithOffsetY = (double)portalPosY + 0.5D;
            loopZMinusEntityZ = (double)portalPosZ + 0.5D;
            int portalDirection = -1;
            /* directions = {"SOUTH", "WEST", "NORTH", "EAST"}; */
            if (this.worldServerInstance.getBlockId(portalPosX - 1, portalPosY, portalPosZ) == portalBlockID)
            {
                portalDirection = 2; // Facing -z we are on the bottom right of the portal area. (north)
            }

            if (this.worldServerInstance.getBlockId(portalPosX + 1, portalPosY, portalPosZ) == portalBlockID)
            {
                portalDirection = 0; // Facing +z South we would be on the bottom right
             }

            if (this.worldServerInstance.getBlockId(portalPosX, portalPosY, portalPosZ - 1) == portalBlockID)
            {
                portalDirection = 3; // facing +x (east) we would be on the bottom right
            }

            if (this.worldServerInstance.getBlockId(portalPosX, portalPosY, portalPosZ + 1) == portalBlockID)
            {
                portalDirection = 1; // facing -x (west) we would be on the bottom right.
            }
            /* directions = {"SOUTH", "WEST", "NORTH", "EAST"}; */
            int entityTeleportDirection = entity.getTeleportDirection();

            if (portalDirection > -1) // If there was a change to the indicator
            {
                int portalDirectionOfOtherPortalBlock = Direction.rotateLeft[portalDirection];
                int portalOrientationOffsetX = Direction.offsetX[portalDirection];
                int portalOrientationOffsetZ = Direction.offsetZ[portalDirection];
                int portalRotatedLeftOffsetX = Direction.offsetX[portalDirectionOfOtherPortalBlock];
                int portalRotatedLeftOffsetZ = Direction.offsetZ[portalDirectionOfOtherPortalBlock];
                boolean flag1 = !this.worldServerInstance.isAirBlock(portalPosX + portalOrientationOffsetX + portalRotatedLeftOffsetX, portalPosY, portalPosZ + portalOrientationOffsetZ + portalRotatedLeftOffsetZ) || !this.worldServerInstance.isAirBlock(portalPosX + portalOrientationOffsetX + portalRotatedLeftOffsetX, portalPosY + 1, portalPosZ + portalOrientationOffsetZ + portalRotatedLeftOffsetZ);
                boolean flag2 = !this.worldServerInstance.isAirBlock(portalPosX + portalOrientationOffsetX, portalPosY, portalPosZ + portalOrientationOffsetZ) || !this.worldServerInstance.isAirBlock(portalPosX + portalOrientationOffsetX, portalPosY + 1, portalPosZ + portalOrientationOffsetZ);

                if (flag1 && flag2)
                {
                    portalDirection = Direction.rotateOpposite[portalDirection];
                    portalDirectionOfOtherPortalBlock = Direction.rotateOpposite[portalDirectionOfOtherPortalBlock];
                    portalOrientationOffsetX = Direction.offsetX[portalDirection];
                    portalOrientationOffsetZ = Direction.offsetZ[portalDirection];
                    portalRotatedLeftOffsetX = Direction.offsetX[portalDirectionOfOtherPortalBlock];
                    portalRotatedLeftOffsetZ = Direction.offsetZ[portalDirectionOfOtherPortalBlock];
                    loopX = portalPosX - portalRotatedLeftOffsetX;
                    portalWithOffsetX -= (double)portalRotatedLeftOffsetX;
                    int i4 = portalPosZ - portalRotatedLeftOffsetZ;
                    loopZMinusEntityZ -= (double)portalRotatedLeftOffsetZ;
                    flag1 = !this.worldServerInstance.isAirBlock(loopX + portalOrientationOffsetX + portalRotatedLeftOffsetX, portalPosY, i4 + portalOrientationOffsetZ + portalRotatedLeftOffsetZ) || !this.worldServerInstance.isAirBlock(loopX + portalOrientationOffsetX + portalRotatedLeftOffsetX, portalPosY + 1, i4 + portalOrientationOffsetZ + portalRotatedLeftOffsetZ);
                    flag2 = !this.worldServerInstance.isAirBlock(loopX + portalOrientationOffsetX, portalPosY, i4 + portalOrientationOffsetZ) || !this.worldServerInstance.isAirBlock(loopX + portalOrientationOffsetX, portalPosY + 1, i4 + portalOrientationOffsetZ);
                }

                float f1 = 0.5F;
                float f2 = 0.5F;

                if (!flag1 && flag2)
                {
                    f1 = 1.0F;
                }
                else if (flag1 && !flag2)
                {
                    f1 = 0.0F;
                }
                else if (flag1 && flag2)
                {
                    f2 = 0.0F;
                }

                portalWithOffsetX += (double)((float)portalRotatedLeftOffsetX * f1 + f2 * (float)portalOrientationOffsetX);
                loopZMinusEntityZ += (double)((float)portalRotatedLeftOffsetZ * f1 + f2 * (float)portalOrientationOffsetZ);
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = 0.0F;

                if (portalDirection == entityTeleportDirection)
                {
                    f3 = 1.0F;
                    f4 = 1.0F;
                }
                else if (portalDirection == Direction.rotateOpposite[entityTeleportDirection])
                {
                    f3 = -1.0F;
                    f4 = -1.0F;
                }
                else if (portalDirection == Direction.rotateRight[entityTeleportDirection])
                {
                    f5 = 1.0F;
                    f6 = -1.0F;
                }
                else
                {
                    f5 = -1.0F;
                    f6 = 1.0F;
                }

                double oldMotionX = entity.motionX;
                double oldMotionZ = entity.motionZ;
                entity.motionX = oldMotionX * (double)f3 + oldMotionZ * (double)f6;
                entity.motionZ = oldMotionX * (double)f5 + oldMotionZ * (double)f4;
                entity.rotationYaw = rotationYaw - (float)(entityTeleportDirection * 90) + (float)(portalDirection * 90);
            }
            else
            {
                entity.motionX = entity.motionY = entity.motionZ = 0.0D;
            }
// Added an offset of +1 to X and Z TODO: names!
//            if (entity instanceof EntityPlayerMP && Reference.DEBUGGING)
//                ((EntityPlayerMP)entity).sendChatToPlayer("Orient:"+Integer.toString(orientationIndicator) +
//                        "| X:" + Integer.toString((int) portalWithOffsetX) + "Y:" + Integer.toString((int) portalWithOffsetY) + "Z:" + Integer.toString((int) loopZMinusEntityZ));
            
            // Sets a +/- 1 inn the direction the portal is facing inn order to prevent player from portal bouncing regardles og motion being zero.
            entity.setLocationAndAngles(portalWithOffsetX + (portalDirection == 1 ? -1 : portalDirection == 3 ? +1 : 0) , portalWithOffsetY, loopZMinusEntityZ + (portalDirection == 2 ? -1 : portalDirection == 0 ? +1 : 0), entity.rotationYaw = (float)(portalDirection * 90), entity.rotationPitch);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean makePortal(Entity par1Entity)
    {
        byte b0 = 16;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(par1Entity.posX);
        int j = MathHelper.floor_double(par1Entity.posY);
        int k = MathHelper.floor_double(par1Entity.posZ);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.random.nextInt(4);
        int i2;
        double d1;
        double d2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        double d3;
        double d4;

        for (i2 = i - b0; i2 <= i + b0; ++i2)
        {
            d1 = (double)i2 + 0.5D - par1Entity.posX;

            for (j2 = k - b0; j2 <= k + b0; ++j2)
            {
                d2 = (double)j2 + 0.5D - par1Entity.posZ;
                label274:

                for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2)
                {
                    if (this.worldServerInstance.isAirBlock(i2, k2, j2))
                    {
                        while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2))
                        {
                            --k2;
                        }

                        for (i3 = l1; i3 < l1 + 4; ++i3)
                        {
                            l2 = i3 % 2;
                            k3 = 1 - l2;

                            if (i3 % 4 >= 2)
                            {
                                l2 = -l2;
                                k3 = -k3;
                            }

                            for (j3 = 0; j3 < 3; ++j3)
                            {
                                for (i4 = 0; i4 < 4; ++i4)
                                {
                                    for (l3 = -1; l3 < 4; ++l3)
                                    {
                                        k4 = i2 + (i4 - 1) * l2 + j3 * k3;
                                        j4 = k2 + l3;
                                        int l4 = j2 + (i4 - 1) * k3 - j3 * l2;

                                        if (l3 < 0 && !this.worldServerInstance.getBlockMaterial(k4, j4, l4).isSolid() || l3 >= 0 && !this.worldServerInstance.isAirBlock(k4, j4, l4))
                                        {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            d4 = (double)k2 + 0.5D - par1Entity.posY;
                            d3 = d1 * d1 + d4 * d4 + d2 * d2;

                            if (d0 < 0.0D || d3 < d0)
                            {
                                d0 = d3;
                                l = i2;
                                i1 = k2;
                                j1 = j2;
                                k1 = i3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D)
        {
            for (i2 = i - b0; i2 <= i + b0; ++i2)
            {
                d1 = (double)i2 + 0.5D - par1Entity.posX;

                for (j2 = k - b0; j2 <= k + b0; ++j2)
                {
                    d2 = (double)j2 + 0.5D - par1Entity.posZ;
                    label222:

                    for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2)
                    {
                        if (this.worldServerInstance.isAirBlock(i2, k2, j2))
                        {
                            while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2))
                            {
                                --k2;
                            }

                            for (i3 = l1; i3 < l1 + 2; ++i3)
                            {
                                l2 = i3 % 2;
                                k3 = 1 - l2;

                                for (j3 = 0; j3 < 4; ++j3)
                                {
                                    for (i4 = -1; i4 < 4; ++i4)
                                    {
                                        l3 = i2 + (j3 - 1) * l2;
                                        k4 = k2 + i4;
                                        j4 = j2 + (j3 - 1) * k3;

                                        if (i4 < 0 && !this.worldServerInstance.getBlockMaterial(l3, k4, j4).isSolid() || i4 >= 0 && !this.worldServerInstance.isAirBlock(l3, k4, j4))
                                        {
                                            continue label222;
                                        }
                                    }
                                }

                                d4 = (double)k2 + 0.5D - par1Entity.posY;
                                d3 = d1 * d1 + d4 * d4 + d2 * d2;

                                if (d0 < 0.0D || d3 < d0)
                                {
                                    d0 = d3;
                                    l = i2;
                                    i1 = k2;
                                    j1 = j2;
                                    k1 = i3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i5 = l;
        int j5 = i1;
        j2 = j1;
        int k5 = k1 % 2;
        int l5 = 1 - k5;

        if (k1 % 4 >= 2)
        {
            k5 = -k5;
            l5 = -l5;
        }

        boolean flag;

        if (d0 < 0.0D)
        {
            if (i1 < 70)
            {
                i1 = 70;
            }

            if (i1 > this.worldServerInstance.getActualHeight() - 10)
            {
                i1 = this.worldServerInstance.getActualHeight() - 10;
            }

            j5 = i1;

            for (k2 = -1; k2 <= 1; ++k2)
            {
                for (i3 = 1; i3 < 3; ++i3)
                {
                    for (l2 = -1; l2 < 3; ++l2)
                    {
                        k3 = i5 + (i3 - 1) * k5 + k2 * l5;
                        j3 = j5 + l2;
                        i4 = j2 + (i3 - 1) * l5 - k2 * k5;
                        flag = l2 < 0;
                        this.worldServerInstance.setBlock(k3, j3, i4, flag ? frameBlockID : 0);
                    }
                }
            }
        }

        for (k2 = 0; k2 < 4; ++k2)
        {
            for (i3 = 0; i3 < 4; ++i3)
            {
                for (l2 = -1; l2 < 4; ++l2)
                {
                    k3 = i5 + (i3 - 1) * k5;
                    j3 = j5 + l2;
                    i4 = j2 + (i3 - 1) * l5;
                    flag = i3 == 0 || i3 == 3 || l2 == -1 || l2 == 3;
                    this.worldServerInstance.setBlock(k3, j3, i4, flag ? frameBlockID : portalBlockID, 0, 2);
                }
            }

            for (i3 = 0; i3 < 4; ++i3)
            {
                for (l2 = -1; l2 < 4; ++l2)
                {
                    k3 = i5 + (i3 - 1) * k5;
                    j3 = j5 + l2;
                    i4 = j2 + (i3 - 1) * l5;
                    this.worldServerInstance.notifyBlocksOfNeighborChange(k3, j3, i4, this.worldServerInstance.getBlockId(k3, j3, i4));
                }
            }
        }

        return true;
    }

    /**
     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
     * WorldServer.getTotalWorldTime() value.
     */
    public void removeStalePortalLocations(long par1)
    {
        if (par1 % 100L == 0L)
        {
            Iterator iterator = this.mazDestinationCoordinateKeys.iterator();
            long j = par1 - 600L;

            while (iterator.hasNext())
            {
                Long olong = (Long)iterator.next();
                PortalPosition portalposition = (PortalPosition)this.mazDestinationCoordinateCache.getValueByKey(olong.longValue());

                if (portalposition == null || portalposition.lastUpdateTime < j)
                {
                    iterator.remove();
                    this.mazDestinationCoordinateCache.remove(olong.longValue());
                }
            }
        }
    }
}

