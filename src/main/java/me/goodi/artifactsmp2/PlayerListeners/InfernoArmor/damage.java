package me.goodi.artifactsmp2.PlayerListeners.InfernoArmor;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.InfernoArmor.InfernoArmorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class damage implements Listener {

    Artifactsmp2 plugin;
    public damage(Artifactsmp2 plugin){this.plugin=plugin;}

    InfernoArmorUtil iutil = new InfernoArmorUtil(plugin);

    @EventHandler
    public void dmg(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player player){
            if(iutil.isFullSet(player)){
                e.getDamager().setFireTicks(60);
            }
        }
    }

}
