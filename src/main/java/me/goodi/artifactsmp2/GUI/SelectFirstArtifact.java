package me.goodi.artifactsmp2.GUI;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
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

public class SelectFirstArtifact {



    public static void openSelectStarterArtifact(Player player){
        Inventory shop = Bukkit.createInventory(null, InventoryType.HOPPER, "§fChoose an artifact");
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        UpgradeArtifact.glassGUI = glass;

        for (int i = 0; i < shop.getSize(); i++) {
            shop.setItem(i, glass);
        }


        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer recallGlovePDC = meta.getPersistentDataContainer();
        recallGlovePDC.set(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER, 1);
        meta.setCustomModelData(CustomModelDataNum.CustomModels.get("RecallGlove"));
        meta.setDisplayName("§fRecall Glove");
        item.setItemMeta(meta);

        shop.setItem(0, item);

        item = new ItemStack(Material.IRON_HORSE_ARMOR);
        meta = item.getItemMeta();
        PersistentDataContainer thirdEyePDC = meta.getPersistentDataContainer();
        thirdEyePDC.set(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER, 1);
        meta.setCustomModelData(CustomModelDataNum.CustomModels.get("ThirdEye"));
        meta.setDisplayName("§fThird Eye");
        item.setItemMeta(meta);


        shop.setItem(1, item);


        item = new ItemStack(Material.STICK);
        meta = item.getItemMeta();
        PersistentDataContainer DemonKingsStaffPDC = meta.getPersistentDataContainer();
        DemonKingsStaffPDC.set(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER, 1);
        meta.setCustomModelData(CustomModelDataNum.CustomModels.get("DemonKingsStaff"));
        meta.setDisplayName("§fDemonKingsStaff");
        item.setItemMeta(meta);

        shop.setItem(2, item);

        player.openInventory(shop);
    }
}
