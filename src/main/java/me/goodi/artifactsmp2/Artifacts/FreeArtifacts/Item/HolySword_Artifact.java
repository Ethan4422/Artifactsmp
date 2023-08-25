package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;

import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.HashMap;
import java.util.UUID;

public class HolySword_Artifact implements Listener {

    private HashMap<UUID, Long>  cooldown = new HashMap<>();

    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        if(e.getItem().getItemMeta().hasCustomModelData()) {
            if (e.getItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("HolySword"))  {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void DamageUndead(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            if(e.getEntity() instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) e.getEntity();
                Player player = (Player) e.getDamager();
                if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("HolySword")) {
                    if (entity.getCategory() == EntityCategory.UNDEAD){
                        e.setDamage(e.getDamage() + 5);
                    }
                    else{
                        e.setDamage(e.getDamage() + 3);
                    }
                }
            }
        }
    }
    @EventHandler
    public void HealEntity(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if(e.getRightClicked() instanceof LivingEntity) {
            if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("HolySword")) {
                LivingEntity entity = (LivingEntity) e.getRightClicked();
                if(!cooldown.containsKey(player.getUniqueId())) {
                    entity.setHealth(entity.getHealth() + 4);
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                }
                else{
                    long timeElasped = (System.currentTimeMillis() - cooldown.get(player.getUniqueId()));
                    if(timeElasped >= 5000){
                        entity.setHealth(entity.getHealth() + 4);
                        cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    }
                    else{
                        player.sendMessage("You are on cooldown for another " + (5000 - timeElasped)/1000 +  " seconds");
                    }
                }
            }
        }
    }
}
