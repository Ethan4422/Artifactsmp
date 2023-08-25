package me.goodi.artifactsmp2.PlayerListeners;

import me.goodi.artifactsmp2.GUI.UpgradeArtifact;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Gui implements Listener {

    @EventHandler
    public void moveItem(InventoryClickEvent e){
        if(e.getInventory().contains(UpgradeArtifact.glassGUI)){
            e.setCancelled(true);
        }
    }

}
