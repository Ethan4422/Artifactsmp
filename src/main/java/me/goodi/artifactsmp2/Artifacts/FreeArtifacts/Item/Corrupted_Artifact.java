package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;


import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;


public class Corrupted_Artifact implements Listener {

    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    Artifactsmp2 plugin;
    public Corrupted_Artifact(Artifactsmp2 plugin){this.plugin = plugin;}



    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        if(e.getItem().getItemMeta().hasCustomModelData()) {
            if (e.getItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("CorruptedArtifact")) {
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
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("CorruptedArtifact")) {
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 70, 0));
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 70, 0));
                        double damage = e.getDamage() + 2;
                        e.setDamage(damage);
                    }
                }
            }
        }
    }


    @EventHandler
    public void TakeDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();
            if(player.getInventory().getItemInMainHand().hasItemMeta() &&player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("CorruptedArtifact")){
                Random random = new Random();
                if(1 + random.nextInt(6) <= 2){
                    e.setCancelled(true);
                }
            }
        }
    }
}
