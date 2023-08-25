package me.goodi.artifactsmp2.BukkitRunnables.RunnableArtifacts;

import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ThirdEyeRun extends BukkitRunnable {
    @Override
    public void run() {
        for (Player people : Bukkit.getOnlinePlayers()) {
            ItemStack item = people.getInventory().getHelmet();
            if (item != null) {
                if (item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("ThirdEye")) {
                    if (people.hasPotionEffect(PotionEffectType.DARKNESS)) {
                        people.removePotionEffect(PotionEffectType.DARKNESS);
                    }
                    if (people.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                        people.removePotionEffect(PotionEffectType.BLINDNESS);
                    }
                }
            }
        }
    }
}
