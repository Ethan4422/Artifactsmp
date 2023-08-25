package me.goodi.artifactsmp2.Commands.Op;

import me.goodi.artifactsmp2.Config.Warps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreateWarp implements CommandExecutor {

    Warps warp = new Warps();


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.isOp()){

                if(strings.length == 1) {
                    warp.setWarp(player, strings[0]);
                }else {
                    player.sendMessage("/createWarp [name]");
                }
            }
        }

        return false;
    }
}
