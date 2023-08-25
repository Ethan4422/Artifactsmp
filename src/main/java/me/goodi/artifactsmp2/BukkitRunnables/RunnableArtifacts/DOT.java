package me.goodi.artifactsmp2.BukkitRunnables.RunnableArtifacts;

import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DOT extends BukkitRunnable {
    @Override
    public void run() {
        for (Player people : Bukkit.getOnlinePlayers()) {
            if (people.getInventory().getItemInMainHand().hasItemMeta() && people.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && people.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("SwordOfTruth")) {
                people.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 1, false, false));
            }
        }
    }
}
