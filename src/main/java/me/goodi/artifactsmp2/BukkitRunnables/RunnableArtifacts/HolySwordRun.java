package me.goodi.artifactsmp2.BukkitRunnables.RunnableArtifacts;

import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class HolySwordRun extends BukkitRunnable {
    @Override
    public void run() {
        for (Player people : Bukkit.getOnlinePlayers()) {
            ItemStack item = people.getInventory().getItemInMainHand();
            if(item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("HolySword")){
                people.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10, 0, false, false));
            }
        }
    }
}
