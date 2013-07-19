package com.mazetar.mazLearnedThis;

import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

import com.mazetar.mazLearnedThis.basemaker.GuiOverlayBaseMaker;
import com.mazetar.mazLearnedThis.biomes.ModBiomes;
import com.mazetar.mazLearnedThis.block.ModBlocks;
import com.mazetar.mazLearnedThis.client.gui.MazGuiOverlay;
import com.mazetar.mazLearnedThis.client.input.*;
import com.mazetar.mazLearnedThis.dimensions.WorldProviderMazMultipleBiomes;
import com.mazetar.mazLearnedThis.dimensions.gen.MazOreGen;
import com.mazetar.mazLearnedThis.entity.ModEntity;
import com.mazetar.mazLearnedThis.fluids.ModFluids;
import com.mazetar.mazLearnedThis.handlers.EventHandlerMaz;
import com.mazetar.mazLearnedThis.item.ModItems;
import com.mazetar.mazLearnedThis.lib.DimensionRef;
import com.mazetar.mazLearnedThis.lib.Reference;
import com.mazetar.mazLearnedThis.network.PacketHandler;
import com.mazetar.mazLearnedThis.proxy.CommonProxy;
import com.mazetar.mazLearnedThis.tileentity.TileEntityFurnaceChest;
import com.mazetar.mazLearnedThis.tileentity.TileEntityObjBuilding;
import com.mazetar.mazLearnedThis.tileentity.TileEntityTest;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class MazLearnedThis {
    
    public static boolean PowerOn = false;
    
    @Instance(Reference.MOD_ID)
    public static MazLearnedThis instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static boolean ShowBlockText = false;
    
    public static GuiOverlayBaseMaker guiOverlayBaseMaker;
    

    private void test(SoundLoadEvent e)
    {
    }
    
// Initialize blocks etc. inside preInit.
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
            config.load();
            ModBlocks.loadIDs(config);
           // ModItems.loadIDs(config);
            
        }
        catch (Exception e)
        {
            FMLLog.log(Level.SEVERE, e, "MazLearnedThis encounterd a problem loading it's configuration file.");
        }
        finally
        {
            if (config.hasChanged())
                config.save();
        }
        
        guiOverlayBaseMaker = new GuiOverlayBaseMaker();
        
        NetworkRegistry.instance().registerGuiHandler(this,new CommonProxy());
        
        // register rendering handlers (Client only)
        proxy.registerRenderThings();
        
        // Initialize the Render Tick Handler (Client only)
        proxy.registerRenderTickHandler();

        // Register the KeyBinding Handler (Client only)
        proxy.registerKeyBindingHandler();

        // Register the Sound Handler (Client only)
        proxy.registerSoundHandler();
        
        

        
     // register rendering handlers (Client only)
        proxy.registerRenderThings();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event){
        
        // Initialize mod blocks, items, entities and biomes.
        ModFluids.Init();
        ModBlocks.init();
        ModItems.init();
        ModEntity.init();
        ModBiomes.init();
        
        MinecraftForge.EVENT_BUS.register(new MazGuiOverlay(Minecraft.getMinecraft()));
        MinecraftForge.EVENT_BUS.register(new EventHandlerMaz());

        GameRegistry.registerTileEntity(TileEntityFurnaceChest.class, "TileEntity Furnace Chest");
        GameRegistry.registerTileEntity(TileEntityTest.class, "TileEntity Test");
        GameRegistry.registerTileEntity(TileEntityObjBuilding.class, "TE Obj Builder");
        
       // DimensionManager.registerProviderType(DimensionRef.MAZ_DIMENSION_ID, WorldProviderMaz.class, false);
        DimensionManager.registerProviderType(DimensionRef.MAZ_DIMENSION_ID, WorldProviderMazMultipleBiomes.class, true);
        DimensionManager.registerDimension(DimensionRef.MAZ_DIMENSION_ID, DimensionRef.MAZ_DIMENSION_ID);
        
        // Register OreGen
        GameRegistry.registerWorldGenerator(new MazOreGen());
        
        ModKeybindings.registerKeyBindings();
        
    }
    
    
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        // Run after ALL mods is loaded. - Code which interacts with other mods here.
    }

    public GuiOverlayBaseMaker GetTopBarGui() {
        return guiOverlayBaseMaker;
    }
    
    
}
