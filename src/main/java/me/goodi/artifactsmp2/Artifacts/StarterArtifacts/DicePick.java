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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class DicePick implements Listener {

    PlayerConfig config = new PlayerConfig();

    private ItemStack RG;
    ArtifactUtil util = new ArtifactUtil();

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
    public void Respawn(PlayerRespawnEvent e) {
        if (RG != null) {
            e.getPlayer().getInventory().addItem(RG);
            RG.setType(Material.AIR);
        }
    }

    @EventHandler
    public void upgrade(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DicePick") && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (player.isSneaking()) {
                UpgradeArtifact.upgradeArtifact(player);
            }
        }
    }

    @EventHandler
    public void upg(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasCustomModelData() && e.getCurrentItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DicePick")) {

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

    @EventHandler
    public void Ability(BlockBreakEvent e){
        Random random = new Random();
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        Integer lvl = container.get(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER);

        for(ItemStack items : e.getBlock().getDrops()){
            if(items.getType() == Material.RAW_COPPER || items.getType() == Material.RAW_GOLD || items.getType() == Material.RAW_IRON || items.getType() == Material.EMERALD || items.getType() == Material.DIAMOND || items.getType() == Material.LAPIS_LAZULI || items.getType() == Material.REDSTONE || items.getType() == Material.COAL){
                if(util.isStarterArtifact(item) && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DicePick")){
                    if(lvl + random.nextInt(7) >= 10){
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), items);
                    }
                }
            }
        }

    }

}
