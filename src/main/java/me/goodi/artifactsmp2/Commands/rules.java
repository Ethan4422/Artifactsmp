package me.goodi.artifactsmp2.Commands;

import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
 public class rules implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player player){
            player.sendMessage("Hello welcome to the artifact smp! Here are a list of rules you must abide by, in order to play the server. \n" +
                    "1. No mods or resourcepacks that give an unfair advantage.\n" +
                    "2. Swearing is allowed besides anything offensive.\n" +
                    "3. No portal Trapping.\n" +
                    "4. You are only allowed to have 1 starter artifact at a time if you get another put it in a chest or just toss it.\n" +
                    "5. Read the rules.");

            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();

            player.playSound(player.getLocation(), "minecraft:rules", SoundCategory.MASTER, 100, 1);

        }

        return false;
    }
}
