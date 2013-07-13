package com.mazetar.mazLearnedThis.client.input;

import java.util.EnumSet;

import com.mazetar.mazLearnedThis.MazLearnedThis;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class MazKeyBind extends KeyHandler {
    private EnumSet tickTypes = EnumSet.of(TickType.CLIENT);

    private String label = "MazKey";
    
    public static boolean keyPressed = false;
    
    
    public MazKeyBind(KeyBinding[] keyBindings, boolean[] repeatings) {
        super(keyBindings, repeatings);
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb,
            boolean tickEnd, boolean isRepeat) {

        // what to do when key is pressed/down
        if (!keyPressed)
        {
            if (kb.keyCode == ModKeybindings.PowerKey.keyBindings[0].keyCode)
            {
                MazLearnedThis.PowerOn = !MazLearnedThis.PowerOn;
            }
            else if (kb.keyCode == ModKeybindings.SomeKey.keyBindings[0].keyCode)
            {
                MazLearnedThis.ShowBlockText = !MazLearnedThis.ShowBlockText;
            }
        }
        
        keyPressed = true;
        
       
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
        // What to do when key is released/up
        keyPressed = false;
    }

    @Override
    public EnumSet<TickType> ticks() {
        return tickTypes;
    }
    
}