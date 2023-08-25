package me.goodi.artifactsmp2.Config;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;


public class Warps {

    public String pluginName = "plugins/Artifactsmp2";

    public void WarpsConfig() {
        File f = getfile();
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

    }

    public Location getWarp(String name) {
        File f = getfile();
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLocation(name);
    }

    public void setWarp(Player player, String name) {
        File f = getfile();
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set(name, player.getLocation());
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWarps(){
        File f = getfile();
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        try {
            yml.save(f);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void delWarp(String name) {
        File f = getfile();
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set(name, null);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public File getfile(){

        return new File(fname());

    }

    public String fname(){
        return pluginName + "/Warps" + ".yml";
    }

}

