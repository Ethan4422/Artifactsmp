package me.goodi.artifactsmp2.Utils;

import me.goodi.artifactsmp2.Artifactsmp2;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class ArtifactUtil {



    public enum Artifacts {
        RECALLGLOVE,
        THIRDEYE,
        DEMONKINGSSTAFF,
        NUKE,
        CORRUPTEDARTIFACT,
        ARESAXE,
        HOLYSWORD,
        POISONKATANA,
        DAWNOFRUIN,
        BEELZEBUB,
        NULLBLADE,
        SWORDOFTRUTH,
        THREADEDBLADE,
        DICEPICK,
        LAWLESSBLADE
    }


    public static ItemStack getArtifactByName(Artifacts name){
        switch (name) {
            case RECALLGLOVE:
                ItemStack item = new ItemStack(Material.WOODEN_SWORD);
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer recallGlovePDC = meta.getPersistentDataContainer();
                recallGlovePDC.set(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER, 1);
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("RecallGlove"));
                meta.setDisplayName("§fRecall Glove");
                item.setItemMeta(meta);
                return item;


            case DICEPICK:
                item = new ItemStack(Material.DIAMOND_PICKAXE);
                meta = item.getItemMeta();
                PersistentDataContainer DicePickPDC = meta.getPersistentDataContainer();
                DicePickPDC.set(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER, 1);
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("DicePick"));
                meta.setDisplayName("§fDice Pickaxe");
                item.setItemMeta(meta);
                return item;


            case THIRDEYE:
                item = new ItemStack(Material.IRON_HORSE_ARMOR);
                meta = item.getItemMeta();
                PersistentDataContainer thirdEyePDC = meta.getPersistentDataContainer();
                thirdEyePDC.set(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER, 1);
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("ThirdEye"));
                meta.setDisplayName("§fThird Eye");
                item.setItemMeta(meta);
                return item;


            case DEMONKINGSSTAFF:
                item = new ItemStack(Material.STICK);
                meta = item.getItemMeta();
                PersistentDataContainer DemonKingsStaffPDC = meta.getPersistentDataContainer();
                DemonKingsStaffPDC.set(new NamespacedKey(Artifactsmp2.getPlugin(), "lvl"), PersistentDataType.INTEGER, 1);
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("DemonKingsStaff"));
                meta.setDisplayName("§fDemonKingsStaff");
                item.setItemMeta(meta);

                return item;
            case NUKE:
                item = new ItemStack(Material.IRON_HORSE_ARMOR);
                meta = item.getItemMeta();
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("Nuke"));
                meta.setDisplayName("§f§lNuke!");
                item.setItemMeta(meta);
                return item;

            case CORRUPTEDARTIFACT:
                item = new ItemStack(Material.IRON_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§5Corrupted Artifact");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("CorruptedArtifact"));
                item.setItemMeta(meta);
                return item;

            case ARESAXE:
                item = new ItemStack(Material.DIAMOND_AXE);
                meta = item.getItemMeta();
                meta.setDisplayName("§eAres's Battle Axe");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("AresHammer"));
                item.setItemMeta(meta);
                return item;

            case HOLYSWORD:
                item = new ItemStack(Material.DIAMOND_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§f§lHoly Sword");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("HolySword"));
                item.setItemMeta(meta);
                return item;

            case POISONKATANA:
                item = new ItemStack(Material.IRON_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§fPoison Katana");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("PoisonKatana"));
                item.setItemMeta(meta);
                return item;

            case DAWNOFRUIN:
                item = new ItemStack(Material.DIAMOND_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§bDawn Of Ruin");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("DawnOfRuin"));
                item.setItemMeta(meta);
                return item;

            case BEELZEBUB:
                item = new ItemStack(Material.DIAMOND_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§4§lBeelzeBub");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("BeelzeBub"));
                item.setItemMeta(meta);
                return item;

            case NULLBLADE:
                item = new ItemStack(Material.NETHERITE_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§0NullBlade");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("NullBlade"));
                item.setItemMeta(meta);
                return item;

            case SWORDOFTRUTH:
                item = new ItemStack(Material.NETHERITE_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§fDagger Of Truth");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("SwordOfTruth"));
                item.setItemMeta(meta);
                return item;

            case THREADEDBLADE:
                item = new ItemStack(Material.DIAMOND_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§bThreadedBlade");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("ThreadedBlade"));
                item.setItemMeta(meta);
                return item;

            case LAWLESSBLADE:
                item = new ItemStack(Material.NETHERITE_SWORD);
                meta = item.getItemMeta();
                meta.setDisplayName("§LawlessBlade");
                meta.setCustomModelData(CustomModelDataNum.CustomModels.get("LawlessBlade"));
                item.setItemMeta(meta);
                return item;

        }
        return new ItemStack(Material.DEBUG_STICK);
    }


    public static void give(Player player, Artifacts name) {
        player.getInventory().addItem(getArtifactByName(Artifacts.valueOf(String.valueOf(name))));
    }

    public void halfSecInvulnerable(Player player){
        player.setInvulnerable(true);
        new BukkitRunnable() {

            @Override
            public void run() {
                player.setInvulnerable(false);
            }
        }.runTaskLater(Artifactsmp2.plugin, 10);
    }

    public boolean isStarterArtifact(ItemStack item){
        if(!item.hasItemMeta() || !item.getItemMeta().hasCustomModelData()){
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        for(int i : CustomModelDataNum.CustomModels.values()){
            if(meta.getCustomModelData() == i && i >= 1081 && i <= 1084){
                return true;
            }
        }
        return false;
    }

}
