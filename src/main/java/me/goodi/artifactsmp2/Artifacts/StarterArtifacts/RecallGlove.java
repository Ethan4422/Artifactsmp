package me.goodi.artifactsmp2.Artifacts.StarterArtifacts;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Config.PlayerConfig;
import me.goodi.artifactsmp2.GUI.UpgradeArtifact;
import me.goodi.artifactsmp2.Utils.ArtifactUtil;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class RecallGlove implements Listener {


    private ItemStack RG;
    private HashMap<UUID, Long> cooldown = new HashMap<>();
    PlayerConfig config = new PlayerConfig();

    ArtifactUtil util = new ArtifactUtil();



    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        if (item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("RecallGlove")) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void dropOnDeath(PlayerDeathEvent e) {
        for (ItemStack item : e.getPlayer().getInventory()) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("RecallGlove")) {
                long timeElasped = (System.currentTimeMillis() - config.getNoobProt(e.getPlayer()));
                if(timeElasped >= 7200000) {
                    RG = item;
                    e.getDrops().remove(item);
                }
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
    public void Ability(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("RecallGlove")) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (!player.isSneaking()) {


                    ItemStack item = player.getInventory().getItemInMainHand();
                    ItemMeta meta = item.getItemMeta();
                    PersistentDataContainer container = meta.getPersistentDataContainer();
                    Integer lvl = container.get(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER);


                    if (!cooldown.containsKey(player.getUniqueId())) {
                        player.teleport(config.getRecallLocation(player));
                        util.halfSecInvulnerable(player);
                        cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    } else {
                        long timeElasped = (System.currentTimeMillis() - cooldown.get(player.getUniqueId()));

                        if (timeElasped >= 25000 - ((lvl*2) * 1000)) {
                            player.teleport(config.getRecallLocation(player));
                            util.halfSecInvulnerable(player);
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        } else {
                            player.sendMessage("You are on cooldown for another " + (25000 - ((lvl*2) * 1000) - timeElasped) / 1000 + " seconds");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void upgrade(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("RecallGlove") && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (player.isSneaking()) {
                UpgradeArtifact.upgradeArtifact(player);
            }
        }
    }

    @EventHandler
    public void upg(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasCustomModelData() && e.getCurrentItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("RecallGlove")) {

            Player player = (Player) e.getWhoClicked();

            if (e.getView().getTitle().equalsIgnoreCase(ChatColor.WHITE + "Upgrade Artifact")) {


                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                Integer lvl = container.get(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER);

                if (lvl != null && lvl + 1 <= 5) {
                    if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), lvl * 10)) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, lvl * 10));
                        container.set(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER, lvl + 1);
                        item.setItemMeta(meta);
                        player.closeInventory();
                    }
                }
            }
        }
    }
}
