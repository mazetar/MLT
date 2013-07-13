package com.mazetar.mazLearnedThis.client.input;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;

public class ModKeybindings {
    
    
    public static MazKeyBind PowerKey;
    public static MazKeyBind SomeKey;
    
    public static void registerKeyBindings(){
        boolean[] repeat = {false};
        KeyBinding[] some = {new KeyBinding("SomeKey", Keyboard.KEY_NUMPAD5)};
        SomeKey = new MazKeyBind(some, repeat);
        KeyBindingRegistry.registerKeyBinding(SomeKey);
        
        KeyBinding[] power = {new KeyBinding("Toggle Power", Keyboard.KEY_G)};
        PowerKey = new MazKeyBind(power, repeat);
        KeyBindingRegistry.registerKeyBinding(PowerKey);
        }

}
