package com.mazetar.mazLearnedThis.block;

import com.mazetar.mazLearnedThis.MazLearnedThis;
import com.mazetar.mazLearnedThis.lib.GuiIds;
import com.mazetar.mazLearnedThis.lib.Reference;
import com.mazetar.mazLearnedThis.tileentity.TileEntityFurnaceChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockFurnaceChest extends BlockContainer {
    
    Icon iconFrontOff;
    Icon iconFrontOn;
    Icon iconSideOff;
    Icon iconSideOn;
    Icon iconBack;
    
    protected BlockFurnaceChest(int bID, Material material) {
        super(bID, material);
        this.setCreativeTab(CreativeTabs.tabTransport);
        this.setUnlocalizedName("Furnace Chest");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityFurnaceChest();
    }
    
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess w, int x,
            int y, int z, int side) {
        // TODO Auto-generated method stub
        return super.getBlockTexture(w, x, y, z, side);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        // 5 first bits of Meta is used for direction
        // 16 8 4 2 1
        int power = meta>>3;
        power = power<<3;
        int direction = meta<<3;
        direction = direction>>3;
        
        // Down, Up, North, South, West, East
        
        if (power == 0){ // OFF
            if (side == direction) // if it's front face.
                return iconFrontOff;
            else if (side == ForgeDirection.OPPOSITES[direction])
                return iconBack;
            else
                return iconSideOff;
        }
        else // ON
        {
            if (side == direction) // if it's front face.
                return iconFrontOn;
            else if (side == ForgeDirection.OPPOSITES[direction])
                return iconBack;
            else
                return iconSideOn;
        }
    }
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        String modId = Reference.MOD_ID.toLowerCase();
        String test = modId + ":BlockFurnaceChestFrontOff";
        iconFrontOff = iconRegister.registerIcon(test);
        iconFrontOn = iconRegister.registerIcon(modId + ":BlockFurnaceChestFrontOn");
        iconSideOff = iconRegister.registerIcon(modId + ":BlockFurnaceChestSideOff");
        iconSideOn = iconRegister.registerIcon(modId + ":BlockFurnaceChestSideOn");
        iconBack = iconRegister.registerIcon(modId + ":BlockFurnaceChestBack");
    }
    
    /**
     * Sets the direction of the block when placed
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
            EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int direction = 0;
        int facing = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (facing == 0) {
            direction = ForgeDirection.NORTH.ordinal();
        }
        else if (facing == 1) {
            direction = ForgeDirection.EAST.ordinal();
        }
        else if (facing == 2) {
            direction = ForgeDirection.SOUTH.ordinal();
        }
        else if (facing == 3) {
            direction = ForgeDirection.WEST.ordinal();
        }

        world.setBlockMetadataWithNotify(x, y, z, direction, 3);

        ((TileEntityFurnaceChest) world.getBlockTileEntity(x, y, z)).setOrientation(direction);
    }
    
    
   
    
    @Override
    public boolean onBlockActivated(World w, int x, int y,
            int z, EntityPlayer player, int par6, float par7,
            float par8, float par9) {
        if (!player.isSneaking())
            player.openGui(MazLearnedThis.instance, GuiIds.FURNACE_CHEST_ID, w, x, y, z);
        
        return true;
        
    }

}
