package me.goodi.artifactsmp2.Commands.Op;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class discord implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            TextComponent message = new TextComponent("Click to join the discord!");
            message.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, "https://discord.gg/BJDSaYGKAD"));
            player.sendMessage( message );

        }

        return false;
    }
}
