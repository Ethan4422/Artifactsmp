package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;


import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.HashMap;
import java.util.UUID;

public class NullBlade implements Listener {

    Artifactsmp2 plugin;

    public NullBlade(Artifactsmp2 plugin) {
        this.plugin = plugin;
    }

    public static HashMap<UUID, Long> TimePauseCooldown = new HashMap<UUID, Long>();
    public static HashMap<UUID, Long> TimePaused = new HashMap<UUID, Long>();


    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        if (e.getItem().getItemMeta().hasCustomModelData()) {
            if (e.getItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("NullBlade")) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void DamageEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (e.getEntity() instanceof LivingEntity) {
                Player player = (Player) e.getDamager();
                LivingEntity entity = (LivingEntity) e.getEntity();
                if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("NullBlade")) {
                        double damage = e.getDamage() + 2;
                        e.setDamage(damage);
                    }
                }
            }
        }
    }

    @EventHandler
    public void StopTime(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("NullBlade") && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (!TimePauseCooldown.containsKey(player.getUniqueId())) {
                TimePauseCooldown.put(player.getUniqueId(), System.currentTimeMillis());
                for (Player people : Bukkit.getOnlinePlayers()) {
                    if (people.getLocation().distance(player.getLocation()) <= 30) {
                        if (people != player) {
                            TimePaused.put(people.getUniqueId(), System.currentTimeMillis());
                        }
                    }
                }
            } else {
                long timeElasped = (System.currentTimeMillis() - TimePauseCooldown.get(player.getUniqueId()));
                if (timeElasped >= 35000) {
                    TimePauseCooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    for (Player people : Bukkit.getOnlinePlayers()) {
                        if (people.getLocation().distance(player.getLocation()) <= 30) {
                            if (people != player) {
                                TimePaused.put(people.getUniqueId(), System.currentTimeMillis());
                            }
                        }
                    }
                } else {
                    player.sendMessage("You are on cooldown for another " + (35000 - timeElasped) / 1000 + " seconds");
                }
            }
        }
    }

    @EventHandler
    public void FrozenPlayerDamaged(EntityDamageEvent e) {
        if (TimePaused.containsKey(e.getEntity().getUniqueId())) {
            e.setCancelled(true);
        }
    }
}