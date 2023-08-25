package me.goodi.artifactsmp2.BukkitRunnables.RunnableArtifacts;

import me.goodi.artifactsmp2.Config.PlayerConfig;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RecallGloveRun extends BukkitRunnable {
    PlayerConfig config = new PlayerConfig();

    @Override
    public void run() {
        for (Player people : Bukkit.getOnlinePlayers()) {
            for(ItemStack item : people.getInventory()){
                if(item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("RecallGlove")){
                    config.setRecallLocation(people, people.getLocation());
                }
            }
        }
    }
}
