package me.goodi.artifactsmp2.BukkitRunnables.RunnableArtifacts.Armor;

import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.InfernoBoots;
import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.InfernoChestplate;
import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.InfernoHelmet;
import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.InfernoLeggings;
import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.InfernoArmor.InfernoArmorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class InfernoArmor extends BukkitRunnable {

    Artifactsmp2 plugin;
    public InfernoArmor(Artifactsmp2 plugin){this.plugin = plugin;}

    InfernoArmorUtil iutil = new InfernoArmorUtil(plugin);

    @Override
    public void run() {
        for(Player people : Bukkit.getOnlinePlayers()){

            if(iutil.isFullSet(people)){
                people.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10, 0, false, false));
            }
        }
    }
}
