package me.goodi.artifactsmp2.Commands.Op;

import me.goodi.artifactsmp2.WorldGenUtil.newWorld;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class testWorld implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender.isOp()){
            newWorld.createWorld(strings[0], World.Environment.valueOf(strings[1].toUpperCase()), WorldType.valueOf(strings[2].toUpperCase()));
        }
        return false;
    }
}
