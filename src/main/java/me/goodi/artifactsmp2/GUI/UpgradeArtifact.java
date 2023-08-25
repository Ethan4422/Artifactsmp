package me.goodi.artifactsmp2.GUI;

import me.goodi.artifactsmp2.Artifactsmp2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class UpgradeArtifact {

    public static ItemStack glassGUI;

    public static void upgradeArtifact(Player player){
        Inventory shop = Bukkit.createInventory(null, InventoryType.HOPPER, "§fUpgrade Artifact");
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        glassGUI = glass;


        for (int i = 0; i < shop.getSize(); i++) {
            shop.setItem(i, glass);
        }

        ItemStack upgBTN = player.getInventory().getItemInMainHand().clone();
        ItemMeta upgBTNMeta = upgBTN.getItemMeta();

        PersistentDataContainer container = upgBTNMeta.getPersistentDataContainer();
        Integer lvl = container.get(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER);

        if (lvl + 1 <= 5) {
            upgBTNMeta.setDisplayName("Upgrade from lvl " + lvl + " to " + (lvl + 1) + " : §aPrice " + lvl  * 10 + " Diamonds!");
        } else {
            upgBTNMeta.setDisplayName("Max Level");
        }
        upgBTN.setItemMeta(upgBTNMeta);
        shop.setItem(0, upgBTN);
        player.openInventory(shop);
    }

}
