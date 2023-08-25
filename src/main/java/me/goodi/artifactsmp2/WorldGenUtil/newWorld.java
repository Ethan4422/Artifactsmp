package me.goodi.artifactsmp2.WorldGenUtil;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class newWorld {

    public static void createWorld(String name, World.Environment worldEnviroment, WorldType worldType){
        WorldCreator wc = new WorldCreator(name);

        wc.environment(worldEnviroment);
        wc.type(worldType);

        wc.createWorld();
    }

}
