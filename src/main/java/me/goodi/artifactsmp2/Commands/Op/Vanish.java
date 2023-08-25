package me.goodi.artifactsmp2.Commands.Op;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Config.PlayerConfig;
import me.goodi.artifactsmp2.Utils.ParticlesUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Vanish implements CommandExecutor, TabCompleter {

    Plugin plugin = Artifactsmp2.plugin;

    PlayerConfig config = new PlayerConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("Vanish") || args[0].equalsIgnoreCase("v")) {
                        if(config.getVanished(player)){
                            config.setVanished(player, false);
                            for(Player people : Bukkit.getOnlinePlayers()){
                                people.showPlayer(plugin, player);
                                if(!args[1].equalsIgnoreCase("true")){
                                    people.sendMessage("§e" + player.getName() + " joined the game");
                                }
                            }
                        }
                        else {
                            config.setVanished(player, true);
                            for(Player people : Bukkit.getOnlinePlayers()){
                                people.hidePlayer(plugin, player);
                                if(!args[1].equalsIgnoreCase("true")) {
                                    people.sendMessage("§e" + player.getName() + " left the game");
                                }
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("isee")) {
                        player.openInventory(Bukkit.getPlayer(args[1]).getInventory());
                    }
                    else if (args[0].equalsIgnoreCase("esee")) {
                        player.openInventory(Bukkit.getPlayer(args[1]).getEnderChest());
                    }
                    else if(args[0].equalsIgnoreCase("test")){
                        ParticlesUtil.drawCircle(player, Float.parseFloat(args[1]));
                    }
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.isOp()) {
            if (args.length == 1) {
                List<String> cmd = new ArrayList<>();

                cmd.add("Vanish");
                cmd.add("isee");
                cmd.add("esee");

                return cmd;
            }
        }
        return null;
    }
}
