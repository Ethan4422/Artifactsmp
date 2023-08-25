package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class SwordOfTruth implements Listener {

    Artifactsmp2 plugin;
    public SwordOfTruth(Artifactsmp2 plugin){this.plugin = plugin;}

    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        if (e.getItem().getItemMeta().hasCustomModelData()) {
            if (e.getItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("SwordOfTruth")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void Ability(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("SwordOfTruth")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                final int damage = 10;
                ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0,0.5,0), EntityType.ARMOR_STAND);

                ItemStack item = player.getInventory().getItemInMainHand();

                as.setArms(true);
                as.setInvisible(true);
                as.setGravity(false);
                as.setSmall(true);
                as.setMarker(true);
                as.setItemInHand(item);
                as.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0),Math.toRadians(0)));

                player.getInventory().remove(item);

                Location dest = player.getLocation().add(player.getLocation().getDirection().multiply(10));
                Vector vector = dest.subtract(player.getLocation()).toVector();

                new BukkitRunnable() {
                    int distance  = 30;
                    int i = 0;
                    @Override
                    public void run() {
                        EulerAngle rot = as.getRightArmPose();
                        EulerAngle newrot = rot.add(20, 0, 0);
                        as.setRightArmPose(newrot);
                        as.teleport(as.getLocation().add(vector.normalize()));

                        if (as.getTargetBlockExact(1) != null && as.getTargetBlockExact(1).isPassable()) {
                            if(as.isDead()){
                                as.remove();
                                if(player.getInventory().firstEmpty() != -1){
                                    player.getInventory().addItem(item);
                                }
                                else{
                                    player.getWorld().dropItemNaturally(player.getLocation(),item);
                                }
                                cancel();
                            }
                        }
                        for(Entity entity : as.getLocation().getChunk().getEntities()){
                            if(!as.isDead()){
                                if(as.getLocation().distance(entity.getLocation()) <= 1.5){
                                    if(entity != player && entity != as){
                                        if(entity instanceof LivingEntity){
                                            LivingEntity livingEntity = (LivingEntity) entity;
                                            livingEntity.damage(damage);
                                        }
                                    }
                                }
                            }
                        }
                        if(i > distance){
                            if(!as.isDead()){
                                as.remove();
                                if(player.getInventory().firstEmpty() != -1){
                                    player.getInventory().addItem(item);
                                }
                                else{
                                    player.getWorld().dropItemNaturally(player.getLocation(),item);
                                }
                                cancel();
                            }
                        }
                        i++;
                    }
                }.runTaskTimer(plugin, 0L, 1L);
            }
        }
    }
}
