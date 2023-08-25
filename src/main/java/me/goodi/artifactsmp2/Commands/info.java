package me.goodi.artifactsmp2.Commands;

import me.goodi.artifactsmp2.Artifactsmp2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class info implements CommandExecutor, TabCompleter {

    Plugin plugin = Artifactsmp2.plugin;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (strings.length < 1) {
                player.sendMessage("/info [starter artifact name!]");
            } else {
                switch (strings[0].toLowerCase()) {
                    case "demonkingsstaff":
                        player.sendMessage("The demon kings staff inflicts damage on entities when you right click them damage = (3 + lvl) so 8 damage at lvl 5.");
                        break;
                    case "thirdeye":
                        player.sendMessage("The third eye makes it so you cant get darkness  / blindness you also take less damage (1.2 + (lvl/10)) so 1.7x less damage at lvl 5.");
                        break;
                    case "recallglove":
                        player.sendMessage("The Recall glove lets you teleport back to where you where 20 seconds before every (25-lvl*2) seconds.");
                        break;
                }

            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> cmd = new ArrayList<>();


            cmd.add("demonkingsstaff");
            cmd.add("thirdeye");
            cmd.add("recallglove");


            final List<String> completions = new ArrayList<>();

            StringUtil.copyPartialMatches(args[0], cmd, completions);


            return completions;
        }
        return null;
    }
}