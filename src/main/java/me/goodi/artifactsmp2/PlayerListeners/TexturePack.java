package me.goodi.artifactsmp2.PlayerListeners;

import me.goodi.artifactsmp2.Artifactsmp2;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TexturePack implements Listener {

    private Artifactsmp2 plugin;



    private final String url = "https://www.dropbox.com/s/en8l0xlxiij751e/Artifacty.zip?dl=0".replace("dl=0", "dl=1");


    @EventHandler
    public void join(PlayerJoinEvent e){
        e.getPlayer().setResourcePack(url);
    }

}
