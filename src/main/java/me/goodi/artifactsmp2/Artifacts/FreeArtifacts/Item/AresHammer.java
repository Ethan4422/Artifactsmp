package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;


import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.HashMap;
import java.util.UUID;


public class AresHammer implements Listener {

    Artifactsmp2 plugin;
    public AresHammer(Artifactsmp2 plugin){this.plugin = plugin;}

    private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();


    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        if (e.getItem().getItemMeta().hasCustomModelData()) {
            if (e.getItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("AresHammer")) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void ClickTp(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("AresHammer") && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Block targetBlock = player.getTargetBlock(null, 10);
            Location blockLoc = targetBlock.getLocation().setDirection(player.getLocation().getDirection());
            if (!cooldown.containsKey(player.getUniqueId())) {
                if(blockLoc.add(0,1,0).getBlock().getType().isAir()) {
                    player.teleport(blockLoc);
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                }
                else {
                    player.sendMessage("§cYou teleported into a wall!");
                }

            }
            else {
                Long timeElasped = (System.currentTimeMillis() - cooldown.get(player.getUniqueId()));
                if(timeElasped >= 3000){
                    player.teleport(blockLoc);
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                }
                else {
                    player.sendMessage("You are on cooldown for another " + (3000 - timeElasped)/1000 + " seconds");
                }
            }
        }
    }
}
