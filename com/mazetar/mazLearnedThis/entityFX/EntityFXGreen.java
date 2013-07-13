package com.mazetar.mazLearnedThis.entityFX;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityFXGreen extends EntityFX {

    public EntityFXGreen(World w, double x, double y,
            double z, double mX, double mY, double mZ) {
        super(w, x, y, z, mX, mY, mZ);
        this.particleGreen = 0.8F;
        this.particleBlue = 0;
        this.particleRed = 0;
    }

}
