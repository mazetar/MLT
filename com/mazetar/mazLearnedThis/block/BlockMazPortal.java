package com.mazetar.mazLearnedThis.block;

import java.util.Random;

import com.mazetar.mazLearnedThis.dimensions.TeleporterMaz;
import com.mazetar.mazLearnedThis.entityFX.EntityFXGreen;
import com.mazetar.mazLearnedThis.lib.DimensionRef;
import com.mazetar.mazLearnedThis.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class BlockMazPortal extends BlockPortal {
    
    // TODO: Move this to dimension ref
    public int frameBlockID = Block.blockIron.blockID;
    
    public BlockMazPortal(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabTransport);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":BlockPortal");
    }
    
    @Override
    public void updateTick(World w, int x, int y, int z, Random random)
    {
        
    }
    
    
    @Override
    public boolean tryToCreatePortal(World w, int x, int y, int z) {
        byte frameOnSideX = 0;
        byte frameOnSideZ = 0;

        if (w.getBlockId(x - 1, y, z) == frameBlockID || w.getBlockId(x + 1, y, z) == frameBlockID)
        {
            frameOnSideX = 1;
        }

        if (w.getBlockId(x, y, z - 1) == frameBlockID || w.getBlockId(x, y, z + 1) == frameBlockID)
        {
            frameOnSideZ = 1;
        }

        if (frameOnSideX == frameOnSideZ)
        {//p.sendChatToPlayer("line 57");
            return false;
        }
        else
        {
            /* Get the starting position */
            if (w.getBlockId(x - frameOnSideX, y, z - frameOnSideZ) == 0)
            {
                x -= frameOnSideX;
                z -= frameOnSideZ;
            }

            int widthLocation;
            int heightLocation;

            for (widthLocation = -1; widthLocation <= 2; ++widthLocation)
            {
                for (heightLocation = -1; heightLocation <= 3; ++heightLocation)
                {
                    boolean shouldBeFrame = widthLocation == -1 || widthLocation == 2 || heightLocation == -1 || heightLocation == 3;

                    if (widthLocation != -1 && widthLocation != 2 || heightLocation != -1 && heightLocation != 3)
                    {
                        int currentBlock = w.getBlockId(x + frameOnSideX * widthLocation, y + heightLocation, z + frameOnSideZ * widthLocation);

                        if (shouldBeFrame)
                        {
                            if (currentBlock != frameBlockID)
                            {//p.sendChatToPlayer("line 85");
                                return false;
                            }
                        } // If it shouldn't be frame, it should either be air or fire
                        else if (currentBlock != 0)
                        {//p.sendChatToPlayer("line 90");
                            return false;
                        }
                    }
                }
            }
            
           // ? w.editingBlocks = true;
            
            /* We are still running so portal frame should be filled by portal */
            for (widthLocation = 0; widthLocation < 2; ++widthLocation)
            {
                for (heightLocation = 0; heightLocation < 3; ++heightLocation)
                {
                    w.setBlock(x + frameOnSideX * widthLocation, y + heightLocation, z + frameOnSideZ * widthLocation, this.blockID, 0, 2);
                }
            }

        // ?    w.editingBlocks = false;
            return true;
        }
        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World w, int x, int y,
            int z, Random random) {
        // Regular portal sound & particle
        if (random.nextInt(100) == 0)
        {
            w.playSound((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "portal.portal", 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int l = 0; l < 4; ++l)
        {
            double d0 = (double)((float)x + random.nextFloat());
            double d1 = (double)((float)y + random.nextFloat());
            double d2 = (double)((float)z + random.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = random.nextInt(2) * 2 - 1;
            d3 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)random.nextFloat() - 0.5D) * 0.5D;

            if (w.getBlockId(x - 1, y, z) != this.blockID && w.getBlockId(x + 1, y, z) != this.blockID)
            {
                d0 = (double)x + 0.5D + 0.25D * (double)i1;
                d3 = (double)(random.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                d2 = (double)z + 0.5D + 0.25D * (double)i1;
                d5 = (double)(random.nextFloat() * 2.0F * (float)i1);
            }

            w.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
        }

        
        // Green particles
        for (int loop = 0; loop < 4; ++loop)
        {
            double randX = (double)((float)x + random.nextFloat());
            double randY = (double)((float)y + random.nextFloat());
            double randZ = (double)((float)z + random.nextFloat());
            double randMotionX = 0.0D;
            double randMotionY = 0.0D;
            double randMotionZ = 0.0D;
            int i1 = random.nextInt(2) * 2 - 1;
            randMotionX = ((double)random.nextFloat() - 0.5D) * 0.5D;
            randMotionY = ((double)random.nextFloat() - 0.5D) * 0.5D;
            randMotionZ = ((double)random.nextFloat() - 0.5D) * 0.5D;

            if (w.getBlockId(x - 1, y, z) != this.blockID && w.getBlockId(x + 1, y, z) != this.blockID)
            {
                randX = (double)x + 0.5D + 0.25D * (double)i1;
                randMotionX = (double)(random.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                randZ = (double)z + 0.5D + 0.25D * (double)i1;
                randMotionZ = (double)(random.nextFloat() * 2.0F * (float)i1);
            }
            EntityFX greenParticle = new EntityFXGreen(w, randX, randY, randZ,
                    randMotionX, randMotionY, randMotionZ);
            Minecraft.getMinecraft().effectRenderer.addEffect(greenParticle);
            //w.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
        }

        /**
         * Spawns a particle.  Args particleName, x, y, z, velX, velY, velZ
         */
       // w.spawnParticle(par1Str, par2, par4, par6, par8, par10, par12);
    }
    
    public void onNeighborBlockChange(World w, int x, int y, int z, int changedBlockID)
    {
     byte portalOnSideX = 0;
     byte portalOnSideZ = 1;

      if (w.getBlockId(x - 1, y, z) == this.blockID || w.getBlockId(x + 1, y, z) == this.blockID)
     {
      portalOnSideX = 1;
      portalOnSideZ = 0;
     }

      int portalBottomPos;

      for (portalBottomPos = y; w.getBlockId(x, portalBottomPos - 1, z) == this.blockID; --portalBottomPos)
     {
      ;
     }

      if (w.getBlockId(x, portalBottomPos - 1, z) != frameBlockID)
     {
      w.setBlock(x, y, z, 0, 0, 2);
     }
     else
     {
      int heightCount;

       for (heightCount = 1; heightCount < 4 && w.getBlockId(x, portalBottomPos + heightCount, z) == this.blockID; ++heightCount)
      {
       ;
      }

       if (heightCount == 3 && w.getBlockId(x, portalBottomPos + heightCount, z) == frameBlockID)
      {
       boolean isPortalBlockOnSideX = w.getBlockId(x - 1, y, z) == this.blockID || w.getBlockId(x + 1, y, z) == this.blockID;
       boolean isPortalBlockOnSideZ = w.getBlockId(x, y, z - 1) == this.blockID || w.getBlockId(x, y, z + 1) == this.blockID;

        if (isPortalBlockOnSideX && isPortalBlockOnSideZ)
       {
        w.setBlock(x, y, z, 0,0,2);
       }
       else
       {
        if ((w.getBlockId(x + portalOnSideX, y, z + portalOnSideZ) != frameBlockID 
                || w.getBlockId(x - portalOnSideX, y, z - portalOnSideZ) != this.blockID) 
                && (w.getBlockId(x - portalOnSideX, y, z - portalOnSideZ) != frameBlockID 
                || w.getBlockId(x + portalOnSideX, y, z + portalOnSideZ) != this.blockID))
        {
         w.setBlock(x, y, z, 0, 0, 2);
        }
       }
      }
      else
      {
       w.setBlock(x, y, z, 0, 0, 2);
      }
     }
    }
    
    @Override
    public void onEntityCollidedWithBlock(World w, int x, int y,
            int z, Entity entity) {
        
        if (entity.ridingEntity == null && entity.riddenByEntity == null)
        {
            if (entity instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP)entity;
                if(player.dimension != DimensionRef.MAZ_DIMENSION_ID)
                {
                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, DimensionRef.MAZ_DIMENSION_ID,
                            new TeleporterMaz(player.mcServer.worldServerForDimension(DimensionRef.MAZ_DIMENSION_ID)));
                    
                }
                else
                {
                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0,
                            new TeleporterMaz(player.mcServer.worldServerForDimension(0)));
                }
                
                
                
            }
        }
        
    }
    
    
}
