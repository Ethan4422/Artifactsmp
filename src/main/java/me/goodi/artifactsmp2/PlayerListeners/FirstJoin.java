package me.goodi.artifactsmp2.PlayerListeners;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Config.PlayerConfig;
import me.goodi.artifactsmp2.Config.Warps;
import me.goodi.artifactsmp2.GUI.SelectFirstArtifact;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class FirstJoin implements Listener {

    Artifactsmp2 plugin;
    Warps warp = new Warps();
    public FirstJoin(Artifactsmp2 plugin){this.plugin=plugin;}
    PlayerConfig config = new PlayerConfig();



    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        config.playerConfig(player);

    }


    @EventHandler
    public void VanJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(config.getVanished(player)){
            for(Player people : Bukkit.getOnlinePlayers()){
                people.hidePlayer(plugin, player);
                e.setJoinMessage("");
            }
        }
        else{
            for(Player people : Bukkit.getOnlinePlayers()){
                if(config.getVanished(people)){
                    player.hidePlayer(plugin,people);
                }
            }
        }
    }
    @EventHandler
    public void leave(PlayerQuitEvent e){
        if(config.getVanished(e.getPlayer())){
            e.setQuitMessage("");
        }
    }


    @EventHandler
    public void selectArtifact(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (config.getStarterArtifact(player).equals("PlaceHolder")) {
            if (e.getView().getTitle().equalsIgnoreCase(ChatColor.WHITE + "Choose an artifact")) {
                if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
                    if (e.getCurrentItem().getItemMeta().hasCustomModelData()) {
                        if (e.getCurrentItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("ThirdEye") || e.getCurrentItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("RecallGlove") || e.getCurrentItem().getItemMeta().getCustomModelData() == CustomModelDataNum.CustomModels.get("DemonKingsStaff")) {
                            config.setStarterArtifact(player, Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName().replace("§f", ""));
                            player.getInventory().addItem(Objects.requireNonNull(e.getCurrentItem()).clone());
                            player.getOpenInventory().close();
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void close(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        if (config.getStarterArtifact(player).equals("PlaceHolder")) {
            if (e.getView().getTitle().equalsIgnoreCase(ChatColor.WHITE + "Choose an artifact")) {

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(config.getStarterArtifact(player).equals("PlaceHolder")) {
                            SelectFirstArtifact.openSelectStarterArtifact(player);
                        }
                        else cancel();
                    }
                }.runTaskLater(Artifactsmp2.getPlugin(), 2L);

            }
        }
    }

    @EventHandler
    public void joinMsg(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!player.hasPlayedBefore()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage("Welcome do /starterArtifact to select your starter artifact you cant change it after so choose wisely\nget info on starter artifacts with /artifactInfo [artifact name] \noh and dont forget to join the discord with /discord!");
                }
            }.runTaskLater(plugin, 40);
            e.getPlayer().teleport(warp.getWarp("spawn"));
        }
    }

    @EventHandler
    public void joinInfo(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(player.isInvulnerable()){
            if(!player.isOp()){
                player.setInvulnerable(false);
            }
        }
    }

}

