package com.mazetar.mazLearnedThis.block;




import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;

// Block registration moved to it's own class!
public class ModBlocks {
    /*  ID  */
    public static int FURNACE_CHEST_ID = 689;
    public static int MAZ_PORTAL_ID = 670;
    public static int BUILDING_CONSTRUCTOR_ID = 671;
    public static int OBJ_BUILDER_ID = 672;
    public static int BLOCK_BOUNDSBLOCK_ID = 673;
    
    /* Name */
    
    public static String NAME_FURNACE_CHEST = "FurnaceChest";
    public static String NAME_MAZ_PORTAL = "MazPortal";
    public static String NAME_BUILDING_CONSTRUCTOR = "BuildingConstructor";
    public static String NAME_OBJ_BUILDER = "ObjBuilder";
    public static String NAME_BLOCK_BOUNDSBLOCK = "BlockBounds";
    
    /* BLOCKS */
    public static BlockFurnaceChest blockFurnaceChest;
    public static BlockMazPortal blockMazPortal;
    public static BlockBuildingConstructor blockTest;
    
    public static BlockObjBuilder blockObjBuilder;
    public static BlockBoundsBlock blockBounds;
    
    public static void loadIDs(Configuration config) {
        FURNACE_CHEST_ID = config.getBlock(NAME_FURNACE_CHEST, FURNACE_CHEST_ID).getInt();
        MAZ_PORTAL_ID = config.getBlock(NAME_MAZ_PORTAL, MAZ_PORTAL_ID).getInt();
        BUILDING_CONSTRUCTOR_ID = config.getBlock(NAME_BUILDING_CONSTRUCTOR, BUILDING_CONSTRUCTOR_ID).getInt();
        OBJ_BUILDER_ID = config.getBlock(NAME_OBJ_BUILDER, OBJ_BUILDER_ID).getInt();
        BLOCK_BOUNDSBLOCK_ID = config.getBlock(NAME_BLOCK_BOUNDSBLOCK, BLOCK_BOUNDSBLOCK_ID).getInt();
        
    }
    
    public static void init(){
        
        blockFurnaceChest = new BlockFurnaceChest(FURNACE_CHEST_ID, Material.anvil);
        blockMazPortal = new BlockMazPortal(MAZ_PORTAL_ID);
        blockTest = new BlockBuildingConstructor(BUILDING_CONSTRUCTOR_ID, "name");
        blockObjBuilder = new BlockObjBuilder(OBJ_BUILDER_ID);
        blockBounds = new BlockBoundsBlock(BLOCK_BOUNDSBLOCK_ID);
        
        GameRegistry.registerBlock(blockFurnaceChest, "FurnaceChest");
        GameRegistry.registerBlock(blockMazPortal, "Maz Portal");
        GameRegistry.registerBlock(blockTest, "Test Block");
        GameRegistry.registerBlock(blockObjBuilder, "Obj Builder");
        GameRegistry.registerBlock(blockBounds, "BoundsBlock");
        
        LanguageRegistry.addName(blockFurnaceChest, "Furnace Chest");
        LanguageRegistry.addName(blockMazPortal, "Maz Portal");
        LanguageRegistry.addName(blockTest, "Test Block");
        LanguageRegistry.addName(blockObjBuilder, "OBJ Builder");
        LanguageRegistry.addName(blockBounds, "BoundsBlock");
        
        
       initializeRecipes();
    }

    private static void initializeRecipes() {
        
        
        
        
    }


    
}
