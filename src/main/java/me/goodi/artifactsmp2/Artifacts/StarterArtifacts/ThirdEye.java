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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

public class ThirdEye implements Listener {

    private ItemStack RG;
    private HashMap<UUID, Long> cooldown = new HashMap<>();
    PlayerConfig config = new PlayerConfig();
    ArtifactUtil util =  new ArtifactUtil();


    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        if (util.isStarterArtifact(item)) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void dropOnDeath(PlayerDeathEvent e) {
        for (ItemStack item : e.getPlayer().getInventory()) {
            if (util.isStarterArtifact(item)) {
                long timeElasped = (System.currentTimeMillis() - config.getNoobProt(e.getPlayer()));
                if(timeElasped >= 7200000) {
                    RG = item;
                    e.getDrops().remove(item);
                }
            }
        }
    }


    @EventHandler
    public void dmg(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            ItemStack item = player.getInventory().getHelmet();
            if (util.isStarterArtifact(item)) {

                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                Integer lvl = container.get(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER);

                e.setDamage(e.getDamage() / (1.2 + (lvl / 10)));

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
    public void rightClick(PlayerInteractEvent e) throws InterruptedException {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (util.isStarterArtifact(item)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (!player.isSneaking()) {
                    if (player.getInventory().getHelmet() != null) {
                        player.getInventory().addItem(player.getInventory().getHelmet());

                        player.getInventory().setHelmet(item);
                        player.getInventory().remove(item);
                    } else {
                        player.getInventory().setHelmet(item);
                        player.getInventory().remove(item);
                    }
                }
            }
        }
    }

    @EventHandler
    public void upgrade(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (util.isStarterArtifact(player.getInventory().getItemInMainHand()) && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("ThirdEye") && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (player.isSneaking()) {
                UpgradeArtifact.upgradeArtifact(player);
            }
        }
    }

    @EventHandler
    public void upg(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasCustomModelData() && e.getCurrentItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("ThirdEye")) {

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