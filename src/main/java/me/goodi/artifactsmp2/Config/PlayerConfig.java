package me.goodi.artifactsmp2.Config;

import me.goodi.artifactsmp2.Utils.ArtifactUtil;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerConfig {

    public String pluginName = "plugins/Artifactsmp2";

    public void playerConfig(Player player) {
        File f = getfile(player);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.addDefault("Name", player.getName());
        yml.addDefault("StarterArtifact", "PlaceHolder");
        yml.addDefault("NoobProt", System.currentTimeMillis());
        yml.addDefault("Vanished", false);
        yml.addDefault("RecallTp", player.getWorld().getSpawnLocation());
        yml.options().copyDefaults(true);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStarterArtifact(Player player) {
        File f = getfile(player);
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getString("StarterArtifact");
    }

    public void setStarterArtifact(Player player, String artifact) {
        File f = getfile(player);
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("StarterArtifact", artifact);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getNoobProt(Player player) {
        File f = getfile(player);
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("NoobProt");
    }

    public Boolean getVanished(Player player) {
        File f = getfile(player);
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getBoolean("Vanished");
    }

    public void setVanished(Player player, Boolean bool) {
        File f = getfile(player);
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Vanished", bool);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Location getRecallLocation(Player player) {
        File f = getfile(player);
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLocation("RecallTp");
    }

    public void setRecallLocation(Player player, Location loc) {
        File f = getfile(player);
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("RecallTp", loc);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public File getfile(Player player){

        return new File(fname(player));

    }

    public String fname(Player player){
        return pluginName + "/PlayerData/" + player.getName() + "/" + player.getUniqueId() + ".yml";
    }

}
