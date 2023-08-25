package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;

import me.goodi.artifactsmp2.GUI.UpgradeArtifact;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.RayTraceResult;

import java.util.HashMap;
import java.util.UUID;

public class Nuke implements Listener {

    private HashMap<UUID, Long> cooldown = new HashMap<>();

    private ItemStack RG;


    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        if (item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("Nuke")) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void dropOnDeath(PlayerDeathEvent e) {
        for (ItemStack item : e.getPlayer().getInventory()) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("Nuke")) {
                RG = item;
                e.getDrops().remove(item);
            }
        }
    }

    @EventHandler
    public void Respawn(PlayerRespawnEvent e) {
        if (RG != null) {
            e.getPlayer().getInventory().addItem(RG);
            RG.setType(Material.AIR);
        }
    }


    @EventHandler
    public void upgrade(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("Nuke") && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (player.isSneaking()) {
                UpgradeArtifact.upgradeArtifact(player);
            }
        }
    }

    @EventHandler
    public void Ability(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.hasItemMeta() && heldItem.getItemMeta().hasCustomModelData() && heldItem.getItemMeta().getCustomModelData() == 999) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {


                if (!cooldown.containsKey(player.getUniqueId())) {

                    RayTraceResult res = player.rayTraceBlocks(60, FluidCollisionMode.ALWAYS);
                    if (res != null && res.getHitBlock() != null) {
                        cooldown.put(player.getUniqueId(), System.currentTimeMillis());

                        LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(res.getHitBlock().getLocation().add(0, 100, 0), EntityType.GIANT);

                        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
                        ItemMeta meta = item.getItemMeta();
                        meta.setCustomModelData(999);
                        item.setItemMeta(meta);

                        entity.setInvisible(true);
                        entity.getEquipment().setItemInMainHand(item);
                        entity.setCustomNameVisible(false);
                        entity.setCollidable(false);
                        entity.setCustomName("Dinnerbone");
                    }

                } else {
                    long timeElasped = (System.currentTimeMillis() - cooldown.get(player.getUniqueId()));

                    if (timeElasped >= 40000) {

                        RayTraceResult res = player.rayTraceBlocks(60, FluidCollisionMode.ALWAYS);
                        if (res != null && res.getHitBlock() != null) {
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis());

                            LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(res.getHitBlock().getLocation().add(0, 100, 0), EntityType.GIANT);

                            ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
                            ItemMeta meta = item.getItemMeta();
                            meta.setCustomModelData(999);
                            item.setItemMeta(meta);

                            entity.setInvisible(true);
                            entity.getEquipment().setItemInMainHand(item);
                            entity.setCustomNameVisible(false);
                            entity.setCollidable(false);
                            entity.setCustomName("Dinnerbone");
                        }
                    } else {
                        player.sendMessage("You are on cooldown for another " + (40000 - timeElasped) / 1000 + " seconds");
                    }
                }
            }
        }
    }
}



