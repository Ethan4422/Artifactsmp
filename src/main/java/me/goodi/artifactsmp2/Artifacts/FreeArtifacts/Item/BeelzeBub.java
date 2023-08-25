package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;

import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;


public class BeelzeBub implements Listener {

    private HashMap<UUID, Long> cooldown = new HashMap<>();
    private HashMap<UUID, Long> cooldown1 = new HashMap<>();

    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        if(e.getItem().getItemMeta().hasCustomModelData()) {
            if (e.getItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("BeelzeBub")) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void DamageUndead(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            if(e.getEntity() instanceof Player) {
                Player target = (Player) e.getEntity();
                Player player = (Player) e.getDamager();
                if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("BeelzeBub")) {
                    if(!cooldown.containsKey(player.getUniqueId())) {
                        Random random = new Random();
                        if(random.nextInt(2) == 0){
                            target.setSaturation(target.getSaturation() - 1);
                        }else {
                            player.setHealth(player.getHealth() + 2);
                        }
                        player.setHealth(player.getHealth() + 2);
                        cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    }
                    else{
                        long timeElasped = (System.currentTimeMillis() - cooldown.get(player.getUniqueId()));
                        if(timeElasped >= 2000){
                            player.setHealth(player.getHealth() + 2);
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void FireEntity(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if(e.getRightClicked() instanceof LivingEntity) {
            if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("BeelzeBub")) {
                LivingEntity entity = (LivingEntity) e.getRightClicked();
                if(!cooldown1.containsKey(player.getUniqueId())) {
                    entity.setFireTicks(60);
                    cooldown1.put(player.getUniqueId(), System.currentTimeMillis());
                }
                else{
                    long timeElasped = (System.currentTimeMillis() - cooldown1.get(player.getUniqueId()));
                    if(timeElasped >= 10000){
                        entity.setFireTicks(60);
                        cooldown1.put(player.getUniqueId(), System.currentTimeMillis());
                    }
                    else{
                        player.sendMessage("You are on cooldown for another " + (10000 - timeElasped)/1000 +  " seconds");
                    }
                }
            }
        }
    }
}
