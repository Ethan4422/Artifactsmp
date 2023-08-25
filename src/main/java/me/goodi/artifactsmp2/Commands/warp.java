package me.goodi.artifactsmp2.Commands;

import me.goodi.artifactsmp2.Config.Warps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class warp implements CommandExecutor, TabCompleter {

    Warps warp = new Warps();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (strings.length == 1) {
                if (warp.getWarp(strings[0]) != null) {
                    if (strings[0].equals("spawn")) {
                        if (!player.getWorld().getName().equals("world") && !player.getWorld().getName().equals("world_nether") && !player.getWorld().getName().equals("world_the_end")) {
                            player.teleport(warp.getWarp(strings[0]));
                        }
                        else {
                            player.sendMessage("§cyou cannot warp to spawn from this world event worlds only!");
                        }

                    } else {
                        player.teleport(warp.getWarp(strings[0]));
                    }
                } else {
                    player.sendMessage("§cinvalid warp!");
                }
            } else {
                player.sendMessage("/warp [Warp name]");
            }

        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command,  String s, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> cmd = new ArrayList<>();


            cmd.add("spawn");
            cmd.add("event");


            final List<String> completions = new ArrayList<>();

            StringUtil.copyPartialMatches(args[0], cmd, completions);


            return completions;
        }
        return null;
    }
}
