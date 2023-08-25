package me.goodi.artifactsmp2.PlayerListeners;

import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropItem implements Listener {

    CustomModelDataNum modelDataNum = new CustomModelDataNum();

    @EventHandler
    public void drop(PlayerDropItemEvent e) {
        ItemStack item = e.getItemDrop().getItemStack();
        if (modelDataNum.shouldGlow(item)) {
            e.getItemDrop().setGlowing(true);
        }
    }

}
