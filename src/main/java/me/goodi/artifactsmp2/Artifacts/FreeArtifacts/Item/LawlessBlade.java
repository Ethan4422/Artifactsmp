package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import me.goodi.artifactsmp2.Utils.ParticlesUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.util.Vector;

public class LawlessBlade implements Listener {

    Artifactsmp2 plugin;
    public LawlessBlade(Artifactsmp2 plugin){this.plugin = plugin;}


    public static Location getRightSide(Location location, double distance) {
        float angle = location.getYaw() / 60;
        return location.clone().subtract(new Vector(Math.cos(angle), 0, Math.sin(angle)).normalize().multiply(distance));
    }


    @EventHandler
    public void Hit(PlayerArmSwingEvent e){
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Location rightHand = getRightSide(player.getEyeLocation(), 0.45).subtract(0, .6, 0); // right hand

        if(item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("LawlessBlade")){

            for(int i=1 ; i < 10 ; i ++) {
                ParticlesUtil.drawCircle(player, 3);
                player.getWorld().spawnParticle(Particle.CLOUD, rightHand, 1);
            }

        }
    }



}
