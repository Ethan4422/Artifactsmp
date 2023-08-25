package me.goodi.artifactsmp2.Utils.InfernoArmor;

import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.InfernoBoots;
import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.InfernoChestplate;
import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.InfernoHelmet;
import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.InfernoLeggings;
import me.goodi.artifactsmp2.Artifactsmp2;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InfernoArmorUtil {
    Artifactsmp2 plugin;
    public InfernoArmorUtil(Artifactsmp2 plugin){this.plugin=plugin;}

    public boolean isFullSet(Player player) {

        InfernoHelmet infernoHelmet = new InfernoHelmet(plugin);
        InfernoChestplate infernoChestplate = new InfernoChestplate(plugin);
        InfernoLeggings infernoLeggings = new InfernoLeggings(plugin);
        InfernoBoots infernoBoots = new InfernoBoots(plugin);

        ItemMeta ihelmet = infernoHelmet.infernoHelmet().getItemMeta();
        ItemMeta ichest = infernoChestplate.infernoChestplate().getItemMeta();
        ItemMeta ileggings = infernoLeggings.infernoLeggings().getItemMeta();
        ItemMeta iboots = infernoBoots.infernoBoots().getItemMeta();

        if (player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null) {
            ItemMeta helmet = player.getInventory().getHelmet().getItemMeta();
            ItemMeta chest = player.getInventory().getChestplate().getItemMeta();
            ItemMeta leggings = player.getInventory().getLeggings().getItemMeta();
            ItemMeta boots = player.getInventory().getBoots().getItemMeta();
            if (helmet.hasCustomModelData() && chest.hasCustomModelData() && leggings.hasCustomModelData() && boots.hasCustomModelData() && helmet.getCustomModelData() == ihelmet.getCustomModelData() && chest.getCustomModelData() == ichest.getCustomModelData() && leggings.getCustomModelData() == ileggings.getCustomModelData() && boots.getCustomModelData() == iboots.getCustomModelData()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
