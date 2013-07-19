package com.mazetar.mazLearnedThis.fluids;

import com.mazetar.mazLearnedThis.block.ModBlocks;
import com.mazetar.mazLearnedThis.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.ItemFluidContainer;

/**
 * Simple bucket which contains MazFluid.
 * Once emptied it will be a empty bucket.
 * If an empty bucket picks up MazFluid, this is the resulting item.
 * @author Mazetar
 *
 */
public class ItemMazFluidBucket extends ItemFluidContainer {

    public ItemMazFluidBucket(int id, int capacity) {
        super(id);
        this.setUnlocalizedName("Bucket of Mazium Fluid");
        this.func_111206_d("Bucket of Liquid Mazium");
    }

    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir) {
        this.itemIcon = ir.registerIcon(Reference.MOD_ID + ":bucket_mazfluid");
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world,
            EntityPlayer player) {


           // world.playSoundAtEntity(player, "mazlt:sound", 1F, 1F);
        
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (movingobjectposition == null)
        {
            return item;
        }
        else
        {
            FillBucketEvent event = new FillBucketEvent(player, item, world, movingobjectposition);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return item;
            }

            if (event.getResult() == Event.Result.ALLOW)
            {
                if (player.capabilities.isCreativeMode)
                {
                    return item;
                }

                if (--item.stackSize <= 0)
                {
                    return event.result;
                }

                if (!player.inventory.addItemStackToInventory(event.result))
                {
                    player.dropPlayerItem(event.result);
                }

                return item;
            }

            if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
            {
                int x = movingobjectposition.blockX;
                int y = movingobjectposition.blockY;
                int z = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, x, y, z))
                {
                    return item;
                }


                if (movingobjectposition.sideHit == 0)
                {
                    --y;
                }

                if (movingobjectposition.sideHit == 1)
                {
                    ++y;
                }

                if (movingobjectposition.sideHit == 2)
                {
                    --z;
                }

                if (movingobjectposition.sideHit == 3)
                {
                    ++z;
                }

                if (movingobjectposition.sideHit == 4)
                {
                    --x;
                }

                if (movingobjectposition.sideHit == 5)
                {
                    ++x;
                }

                if (!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, item))
                {
                    return item;
                }

                if (this.tryPlaceContainedLiquid(world, x, y, z) && !player.capabilities.isCreativeMode)
                {
                    return new ItemStack(Item.bucketEmpty);
                }
                
            }

            return item;
        }
    }
    
    /**
     * Attempts to place the MazFluid contained inside the bucket.
     */
    public boolean tryPlaceContainedLiquid(World w, int x, int y, int z)
    {

            Material material = w.getBlockMaterial(x, y, z);
            boolean isNotSolid = !material.isSolid();

            if (!w.isAirBlock(x, y, z) && !isNotSolid)
            {
                return false;
            }
            else
            {

                    if (!w.isRemote && isNotSolid && !material.isLiquid())
                    {
                        w.destroyBlock(x, y, z, true);
                    }
                    w.setBlock(x, y, z, ModBlocks.MAZ_FLUID_ID, 0, 3);
                return true;
            }
        
    }

}
