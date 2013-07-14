package com.mazetar.mazLearnedThis.basemaker;

import com.mazetar.mazLearnedThis.client.model.ModelMazBase;
import com.mazetar.mazLearnedThis.lib.Reference;

public class Building {
    private String owner;
    public int Power; 
    public int MaxHealth;
    public int CurrentHealth;
    public ModelMazBase Model;
    
    public int BuildTimeInTicks = 120 * Reference.TICKS_PER_SEC; // default 2minutes.
    
    public static int GetTicksFromSeconds(int seconds)
    {
        return seconds * Reference.TICKS_PER_SEC;
    }
    
    
    public boolean IsTheOwner(String playerName)
    {
        return playerName.toLowerCase() == owner;
    }
    
    public void setOwner(String newOwner)
    {
        owner = newOwner.toLowerCase();
    }
    
    public float GetHealthPercent()
    {
        return (CurrentHealth/MaxHealth);
    }
    
}
