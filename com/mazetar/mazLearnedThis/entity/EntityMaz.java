package com.mazetar.mazLearnedThis.entity;

import com.mazetar.mazLearnedThis.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityMaz extends EntityMob {

    public int ItemDroppedID;
    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/entities/EntityMaz");
    public EntityMaz(World w) {
        super(w);
 //       this.texture = "/mods/mazlt/textures/entity/EntityMaz.png";
        addAiTasks();
        ItemDroppedID = Item.appleRed.itemID;
    }
    
    

    private void addAiTasks() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWander(this, 0.1F));

        // Target Tasks
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 1, true));
    }
    
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    /* SOUNDS */

    protected String getLivingSound() {
        return null;
    }

    protected String getHurtSound() {
        return null;
    }

    protected String getDeathSound() {
        return null;
    }

    protected void playStepSound(int par1, int par2, int par3, int par4) {
        // this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F,
        // 1.0F);
    }

    /* End SOUNDS */

    @Override
    protected int getDropItemId() {
        return ItemDroppedID;
    }

    @Override
    protected void dropRareDrop(int par1) {
        switch (this.rand.nextInt(2)) {
            case 0:
                this.dropItem(Item.ingotIron.itemID, 1);
                break;
            default:
                this.dropItem(Item.book.itemID, 2);

        }
    }

    protected void dropFewItems(boolean par1, int par2) {
        if (this.rand.nextInt(3) == 0) {
            this.dropItem(Item.bakedPotato.itemID, 1);
        }
    }

    
    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        // TODO Auto-generated method stub

        this.worldObj.playSoundAtEntity(this, Reference.MOD_ID + ":sound1", 1.0F, 1.0F);
        super.onCollideWithPlayer(par1EntityPlayer);
    }


    @Override
    protected boolean isAIEnabled() {
        return true;
    }

}
