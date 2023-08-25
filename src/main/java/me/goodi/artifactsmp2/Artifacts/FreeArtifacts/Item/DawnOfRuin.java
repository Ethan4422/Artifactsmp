package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;

import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class DawnOfRuin implements Listener {

    private HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        if (e.getItem().getItemMeta().hasCustomModelData()) {
            if (e.getItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DawnOfRuin")) {
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
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DawnOfRuin")) {
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
                        double damage = e.getDamage() + 2;
                        e.setDamage(damage);
                    }
                }
            }
        }
    }

    @EventHandler
    public void Ability(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DawnOfRuin") && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (!cooldown.containsKey(player.getUniqueId())) {
                for (Player people : Bukkit.getOnlinePlayers()) {
                    if (people.getLocation().distance(player.getLocation()) <= 30) {
                        if (people != player) {
                            people.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1200, 0));
                        }
                    }
                }
                cooldown.put(player.getUniqueId(), System.currentTimeMillis());
            } else {
                long timeElasped = (System.currentTimeMillis() - cooldown.get(player.getUniqueId()));

                if (timeElasped >= 10000) {
                    for (Player people : Bukkit.getOnlinePlayers()) {
                        if (people.getLocation().distance(player.getLocation()) <= 30) {
                            if (people != player) {
                                people.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1200, 0));
                            }
                        }
                    }
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                } else {
                    player.sendMessage("You are on cooldown for another " + (10000 - timeElasped) / 1000 + " seconds");
                }
            }
        }
    }
}
