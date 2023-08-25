package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;

import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonKatana implements Listener {

    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        if(e.getItem().getItemMeta().hasCustomModelData()) {
            if (e.getItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("PoisonKatana")) {
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
                if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("PoisonKatana")) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1));
                    e.setDamage(e.getDamage() + 2);
                }
            }
        }
    }

}
