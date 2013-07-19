package com.mazetar.mazLearnedThis.client.sounds;

import com.mazetar.mazLearnedThis.lib.Reference;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundEvents {
    
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event) {
    // You add them the same way as you add blocks, mod_name is your modID.
        event.manager.addSound("mazlt:sound1.ogg");
        event.manager.addSound("mazlt:sound2.ogg");
        
        
        
    }

}
