package me.goodi.artifactsmp2.PlayerListeners;

import me.goodi.artifactsmp2.Config.PlayerConfig;
import me.goodi.artifactsmp2.Config.Warps;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    PlayerConfig config = new PlayerConfig();
    Warps warp = new Warps();

    @EventHandler
    public void Death(PlayerDeathEvent e){
        long timeElasped = (System.currentTimeMillis() - config.getNoobProt(e.getPlayer()));
        if(timeElasped >= 7200000){
            if(!e.getKeepInventory()) {
                e.setKeepInventory(false);
            }
        }
        else {
            e.getPlayer().sendMessage("§aYou kept your inventory because of noob prot!");
            e.setKeepInventory(true);
            e.getDrops().clear();
        }
        e.getPlayer().teleport(warp.getWarp("spawn"));
    }

}
