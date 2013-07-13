package com.mazetar.mazLearnedThis.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.mazetar.mazLearnedThis.MazLearnedThis;
import com.mazetar.mazLearnedThis.lib.Reference;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModEntity {
    
    static int startEntityId;

    public static void init() {
        startEntityId = 300;
        
        MazLearnedThis mod = MazLearnedThis.instance;

        /*
         * registerModEntity(Entity Class, EntityName, EntityID, Mod, Tracking
         * Range, Update Frequency, updateVelocityToServer); Mod = Mod instance
         * | Tracking Range = The max range the entity can be at, and still get
         * updated. Frequency = how often it's update method is called, 1 =
         * every tick = at 20ticks/second. updateVelocityToServer = Wether it
         * will send velocity updates to the server.
         */
        EntityRegistry.registerModEntity(EntityMaz.class, "Maz Mob", 1, mod,
                50, 1, true);

        /*
         * addSpawn(Entity Class, SpawnChance, minPackSize, maxPackSize,
         * CreatureType, Biomes...) Spawn chance = 1 rare while 10 is quite
         * common. Min & Max pack size is the ammount of creatures that can
         * spawn together. Biomes... Every parameter from here onn is a biome it
         * can spawn inn.
         */
        EntityRegistry.addSpawn(EntityMaz.class, 10, 1, 3,
                EnumCreatureType.creature, BiomeGenBase.beach,
                BiomeGenBase.plains, BiomeGenBase.desert);

        // Language Registry fix to fix the bugged name it has ingame.
        // The first parameter is a bit harder. It is:
        // entity.modid.mobname.name.
        // 2nd is the real name it should have.
        LanguageRegistry.instance().addStringLocalization("entity.mazlt.Maz Mob.name", "MazMonsterMob");
        
        // Register spawning eggs  args: class, primaryEggColor, secondaryEggColor
        registerEntityEgg(EntityMaz.class, 0xBB1DF0, 0x1DF0B4);
    }
    
    public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
    {
     int id = getUniqueEntityId();
     EntityList.IDtoClassMapping.put(id, entity);
     EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
    }
    
    public static int getUniqueEntityId() 
    {
     do 
     {
      startEntityId++;
     } 
     while (EntityList.getStringFromID(startEntityId) != null);

      return startEntityId;
    }
}
