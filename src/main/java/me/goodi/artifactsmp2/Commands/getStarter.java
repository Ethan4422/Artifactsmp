package me.goodi.artifactsmp2.Commands;

import me.goodi.artifactsmp2.Config.PlayerConfig;
import me.goodi.artifactsmp2.GUI.SelectFirstArtifact;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class getStarter implements CommandExecutor {
    PlayerConfig config = new PlayerConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(config.getStarterArtifact(player).equals("PlaceHolder")) {
                player.playSound(player.getLocation(), "minecraft:wisely", SoundCategory.MASTER, 100, 1);
                SelectFirstArtifact.openSelectStarterArtifact(player);
            }
        }
        return false;
    }
}
