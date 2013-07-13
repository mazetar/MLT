package com.mazetar.mazLearnedThis.buildingConstruction;

public class BuildingPlans {

    int[][][] BuildingPlan;
    public int BlocksPerBuildTick;
    public int TicksBetweenBuildTicks;
    public String BuildingName;
    
    public BuildingPlans(int blocksPerBuildTick, int ticksBetweenBuildTicks, String name, int[][][] plan)
    {
        BuildingPlan = plan;
        BlocksPerBuildTick = blocksPerBuildTick;
        TicksBetweenBuildTicks = ticksBetweenBuildTicks;
        BuildingName = name;
    }
    
    public int getSizeZ(int x, int y)
    {
        return BuildingPlan[x][y].length;
    }
    
    public int getSizeY(int x)
    {
        return BuildingPlan[x].length;
    }
    
    public int getSizeX()
    {
        return BuildingPlan.length;
    }
    
    public int getIdAt(int x, int y, int z)
    {
        if (getSizeX() >= x)
            if (getSizeY(x) >= y)
                if (getSizeZ(x,y) >= z)
                {
                    return BuildingPlan[x][y][z];
                }
        
        return -1;
    }
    
}
