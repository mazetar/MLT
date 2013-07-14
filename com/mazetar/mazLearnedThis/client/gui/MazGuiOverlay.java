package com.mazetar.mazLearnedThis.client.gui;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;

import com.mazetar.mazLearnedThis.MazLearnedThis;
import com.mazetar.mazLearnedThis.basemaker.GuiOverlayBaseMaker;
import com.mazetar.mazLearnedThis.block.ModBlocks;
import com.mazetar.mazLearnedThis.lib.Reference;

public class MazGuiOverlay extends Gui {

    private Minecraft mc;
    private GuiOverlayBaseMaker guiOverlayBaseMaker;
    public MazGuiOverlay(Minecraft minecraft) {
        super();
        mc = minecraft;
        guiOverlayBaseMaker = MazLearnedThis.instance.GetTopBarGui();
    }

    private static final int BUFF_ICON_SIZE = 18;
    private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2; // 2 pixels
                                                                     // between
                                                                     // bufff
                                                                     // icons.

    private static final int BUFF_ICON_BASE_U_OFFSET = 0;
    private static final int BUFF_ICON_BASE_V_OFFSET = 198;
    private static final int BUFF_ICONS_PER_ROW = 8;

    //
    // This event is called by GuiIngameForge during each frame by
    // GuiIngameForge.pre() and GuiIngameForce.post().
    //
    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void DrawBuffIcons(RenderGameOverlayEvent event) {
        //
        // We draw after the ExperienceBar has drawn. The event raised by
        // GuiIngameForge.pre()
        // will return true from isCancelable. If you call
        // event.setCanceled(true) in
        // that case, the portion of rendering which this event represents will
        // be canceled.
        // We want to draw *after* the experience bar is drawn, so we make sure
        // isCancelable() returns
        // false and that the eventType represents the ExperienceBar event.
        if (event.isCancelable() || event.type != ElementType.EXPERIENCE) {
            return;
        }
        
        // Starting position for the buff bar - 2 pixels from the top left
        // corner.
        int xPos = 2;
        int yPos = 2;
        Collection collection = this.mc.thePlayer.getActivePotionEffects();
        if (!collection.isEmpty()) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            this.mc.renderEngine.func_110577_a(new ResourceLocation(Reference.MOD_ID, "/gui/inventory.png"));

            for (Iterator iterator = this.mc.thePlayer.getActivePotionEffects()
                    .iterator(); iterator.hasNext(); xPos += BUFF_ICON_SPACING) {
                PotionEffect potioneffect = (PotionEffect) iterator.next();
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];

                if (potion.hasStatusIcon()) {
                    int iconIndex = potion.getStatusIconIndex();
                    this.drawTexturedModalRect(xPos, yPos,
                            BUFF_ICON_BASE_U_OFFSET + iconIndex
                                    % BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
                            BUFF_ICON_BASE_V_OFFSET + iconIndex
                                    / BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
                            BUFF_ICON_SIZE, BUFF_ICON_SIZE);
                }
            }
        }

    }

    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void DrawGuiOverlays(RenderGameOverlayEvent event) {
        //
        // We draw after the ExperienceBar has drawn. The event raised by
        // GuiIngameForge.pre()
        // will return true from isCancelable. If you call
        // event.setCanceled(true) in
        // that case, the portion of rendering which this event represents will
        // be canceled.
        // We want to draw *after* the experience bar is drawn, so we make sure
        // isCancelable() returns
        // false and that the eventType represents the ExperienceBar event.
        if (event.isCancelable() || event.type != ElementType.EXPERIENCE) {
            return;
        }
        
        
        guiOverlayBaseMaker.DrawTopBar(event, this.mc);
        
        
        // Test code:
        String txt = "OFFLINE";
        int color = 0x990000;
        if (MazLearnedThis.PowerOn) {
            txt = "ONLINE";
            color = 0x66CC00;
        }
        this.drawString(mc.fontRenderer, txt, 200, 10, color);

        if (MazLearnedThis.ShowBlockText) {
            MovingObjectPosition mop = this.mc.renderViewEntity.rayTrace(100,
                    0.5F);
            if (mop != null) {
                if (mop.entityHit != null) {
                    this.drawString(mc.fontRenderer,
                            mop.entityHit.getEntityName(), 200, 50, 0x66CC00);
                    this.drawString(
                            mc.fontRenderer,
                            ""
                                    + mop.entityHit
                                            .getDistanceSqToEntity(mc.thePlayer),
                            200, 60, 0x66CC00);
                } else {
                    Block block = Block.blocksList[this.mc.thePlayer.worldObj
                            .getBlockId(mop.blockX, mop.blockY, mop.blockZ)];
                    int meta = this.mc.thePlayer.worldObj.getBlockMetadata(
                            mop.blockX, mop.blockY, mop.blockZ);
                    TileEntity te = this.mc.thePlayer.worldObj
                            .getBlockTileEntity(mop.blockX, mop.blockY,
                                    mop.blockZ);
                    this.drawString(mc.fontRenderer, block.getLocalizedName(),
                            200, 60, 0x66CC00);
                    this.drawString(mc.fontRenderer, "" + meta, 200, 70,
                            0x66CC00);

                    if (te != null)
                        this.drawString(mc.fontRenderer, "Has TileEntity", 200,
                                80, 0x66CC00);

                }
                
                
                
            }
        }

    }

    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void test(RenderWorldLastEvent event)
    {
        
            if (!mc.theWorld.isRemote)
                return;
            
            double doubleX = mc.thePlayer.posX - 0.5;
            double doubleY = mc.thePlayer.posY + 0.1;
            double doubleZ = mc.thePlayer.posZ - 0.5;

            GL11.glPushMatrix();
            GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
            GL11.glColor3ub((byte)255,(byte)0,(byte)0);
            float posX = -400;
            float posY = 71+0.15F;
            float posZ = 166;
            float offset = 0.55F;
            
            DrawingUtil.DrawCubeLines(posX + 10, posY+2, posZ, posX+20, posY+7, posZ+10);
            
            GL11.glPointSize(50.0F);
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glVertex3f(posX-3f,posY,posZ+1f);
            GL11.glEnd();
            
            GL11.glLineWidth(10F);
            GL11.glBegin(GL11.GL_LINES);
            
            // TOP
            GL11.glVertex3f(posX+offset,posY,posZ+offset);
            GL11.glVertex3f(posX+offset,posY,posZ-offset);
            
            GL11.glVertex3f(posX-offset,posY,posZ+offset);
            GL11.glVertex3f(posX-offset,posY,posZ-offset);
            
            GL11.glVertex3f(posX-offset,posY,posZ-offset);
            GL11.glVertex3f(posX+offset,posY,posZ-offset);
            
            GL11.glVertex3f(posX-offset,posY,posZ+offset);
            GL11.glVertex3f(posX+offset,posY,posZ+offset);
            
            // Bottom
            GL11.glVertex3f(posX+offset,posY-1,posZ+offset);
            GL11.glVertex3f(posX+offset,posY-1,posZ-offset);
            
            GL11.glVertex3f(posX-offset,posY-1,posZ+offset);
            GL11.glVertex3f(posX-offset,posY-1,posZ-offset);
            
            GL11.glVertex3f(posX-offset,posY-1,posZ-offset);
            GL11.glVertex3f(posX+offset,posY-1,posZ-offset);
            
            GL11.glVertex3f(posX-offset,posY-1,posZ+offset);
            GL11.glVertex3f(posX+offset,posY-1,posZ+offset);
            
            // SIDES
            GL11.glVertex3f(posX+offset,posY-1,posZ+offset);
            GL11.glVertex3f(posX+offset,posY,posZ+offset);
            
            GL11.glVertex3f(posX-offset,posY-1,posZ-offset);
            GL11.glVertex3f(posX-offset,posY,posZ-offset);
            
            GL11.glVertex3f(posX-offset,posY-1,posZ+offset);
            GL11.glVertex3f(posX-offset,posY,posZ+offset);
            
            GL11.glVertex3f(posX+offset,posY-1,posZ-offset);
            GL11.glVertex3f(posX+offset,posY,posZ-offset);
            
//            GL11.glVertex3f(posX-offset,posY-1,posZ-offset);
//            GL11.glVertex3f(posX+offset,posY-1,posZ-offset);
//            
//            GL11.glVertex3f(posX-offset,posY-1,posZ+offset);
//            GL11.glVertex3f(posX+offset,posY-1,posZ+offset);
//            
            
            GL11.glEnd();
            
            float x = posX + 3 +0.5F;
            float z = posZ + 0 + 0.5F;
            float y = posY;
            
            GL11.glBegin(GL11.GL_TRIANGLES);
            
            GL11.glVertex3f(x + 1,posY,z);
            GL11.glVertex3f(x,posY,z);
            GL11.glVertex3f(x,posY,z+1);
            GL11.glEnd();
            
            GL11.glColor4ub((byte)0,(byte)255,(byte)0, (byte) 50);
            GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glVertex3f(x + 1,posY,z);
            GL11.glVertex3f(x,posY,z+1);
            GL11.glVertex3f(x+1,posY,z+1);
            
            GL11.glEnd();
            
            
            
            
            GL11.glPopMatrix();

            //if it was 0, it would be on the same plane as the face of the block. This is to prevent the graphical glitch
            double zOffset = 0.01;
           
            //bind the new texture
     //       GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/gui/items.png"));
           
            //draw the overlay quad
//            t.addVertexWithUV(x, y, z+1 + zOffset, 0, 1);
//            t.addVertexWithUV(x+1, y, z+1 + zOffset, 1, 1);
//            t.addVertexWithUV(x+1, y+1, z+1 + zOffset, 1, 0);
//            t.addVertexWithUV(x, y+1, z+1 + zOffset, 0, 0);
//           

    }
}

