package me.goodi.artifactsmp2.Artifacts.StarterArtifacts;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Config.PlayerConfig;
import me.goodi.artifactsmp2.GUI.UpgradeArtifact;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.apache.commons.lang3.Validate;
import org.bukkit.*;
import org.bukkit.entity.*;
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
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class DemonKingStaff implements Listener {

    private HashMap<UUID, Long> cooldown = new HashMap<>();
    PlayerConfig config = new PlayerConfig();

    private ItemStack RG;


    @EventHandler
    public void DamageItem(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        if (item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DemonKingsStaff")) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void dropOnDeath(PlayerDeathEvent e) {
        for (ItemStack item : e.getPlayer().getInventory()) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DemonKingsStaff")) {
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
        if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DemonKingsStaff") && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (player.isSneaking()) {
                UpgradeArtifact.upgradeArtifact(player);
            }
        }
    }

    @EventHandler
    public void upg(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasCustomModelData() && e.getCurrentItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DemonKingsStaff")) {

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

    public void drawLine(Player player, Location point1, Location point2, double space, int size) {
        World world = point1.getWorld();
        Validate.isTrue(point2.getWorld().equals(world), "Lines cannot be in different worlds!");
        double distance = point1.distance(point2);
        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0;
        for (; length < distance; p1.add(vector)) {

            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK, size);
            player.getWorld().spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 1, dustOptions);


            length += space;

        }
    }

    @EventHandler
    public void Ability(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item1 = player.getInventory().getItemInMainHand();

        if (item1.hasItemMeta() && item1.getItemMeta().hasCustomModelData() && item1.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DemonKingsStaff") && e.getAction().isRightClick()) {


            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            Integer lvl = container.get(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER);

            if (!cooldown.containsKey(player.getUniqueId())) {

                RayTraceResult res = player.rayTraceEntities(30, false);
                if (res != null && res.getHitEntity() != null) {
                    LivingEntity entity = (LivingEntity) res.getHitEntity();

                    Location loc = entity.getLocation();
                    int radius = 4;
                    for (int x = (loc.getBlockX() - radius); x <= (loc.getBlockX() + radius); x++) {
                        for (int y = (loc.getBlockY() - radius); y <= (loc.getBlockY() + radius); y++) {
                            for (int z = (loc.getBlockZ() - radius); z <= (loc.getBlockZ() + radius); z++) {
                                Location l = new Location(loc.getWorld(), x, y, z);
                                if (l.distance(loc) <= radius) {
                                    drawLine(e.getPlayer(), l.add(0, 25, 0), loc, 6, 3);
                                }
                            }
                        }
                        entity.playEffect(EntityEffect.HURT);
                        entity.damage(3 + lvl);
                        cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    }
                }


            } else {
                long timeElasped = (System.currentTimeMillis() - cooldown.get(player.getUniqueId()));

                if (timeElasped >= 10000 - (lvl * 1000)) {
                    RayTraceResult res = player.rayTraceEntities(30, false);
                    if (res != null && res.getHitEntity() != null) {
                        LivingEntity entity = (LivingEntity) res.getHitEntity();

                        Location loc = entity.getLocation();
                        int radius = 4;
                        for (int x = (loc.getBlockX() - radius); x <= (loc.getBlockX() + radius); x++) {
                            for (int y = (loc.getBlockY() - radius); y <= (loc.getBlockY() + radius); y++) {
                                for (int z = (loc.getBlockZ() - radius); z <= (loc.getBlockZ() + radius); z++) {
                                    Location l = new Location(loc.getWorld(), x, y, z);
                                    if (l.distance(loc) <= radius) {
                                        drawLine(e.getPlayer(), l.add(0, 25, 0), loc, 6, 3);
                                    }
                                }
                            }
                            entity.playEffect(EntityEffect.HURT);
                            entity.damage(3 + lvl);
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        }
                    }
                } else {
                    player.sendMessage("You are on cooldown for another " + (10000 - (lvl * 1000) - timeElasped) / 1000 + " seconds");
                }
            }

        }
    }
}
