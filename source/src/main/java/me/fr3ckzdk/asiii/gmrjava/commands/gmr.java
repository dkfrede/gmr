package me.fr3ckzdk.asiii.gmrjava.commands;

import me.fr3ckzdk.asiii.gmrjava.GmrJava;
import me.fr3ckzdk.asiii.gmrjava.utils.getBan;
import me.fr3ckzdk.asiii.gmrjava.utils.getLogo;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;


public class gmr implements CommandExecutor {

    private GmrJava plugin = GmrJava.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("discord")) {
                    p.sendMessage(getLogo.getLogo() + " §7GmR's discord: §6https://discord.gg/QuzdXtCMjs");
                } else if (args[0].equalsIgnoreCase("reload")){
                    if (p.hasPermission(plugin.getConfig().getString("staff.gmr"))) {
                        plugin.reloadConfig();
                        p.sendMessage(getLogo.getLogo() + ChatColor.GRAY + " Du har reloaded config filen!");
                    }
                } else if (args[0].equalsIgnoreCase("check")) {
                    Player player = Bukkit.getServer().getPlayer(args[1]);
                    if (player != null) {
                        try {
                            if (getBan.getIfBanned(player)) {
                                p.sendMessage(getLogo.getLogo() + ChatColor.GRAY + " Denne spiller er banlyst fra GmR, og har §6"+getBan.getWarns(player));
                            } else {
                                p.sendMessage(getLogo.getLogo() + ChatColor.GRAY + " Denne spiller er ikke banlyst fra GmR");
                            }
                            } catch (IOException e) {
                            p.sendMessage(ChatColor.RED + "Fejlede med at tjekke om spillern er banlyst");
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else {
                p.sendMessage(getLogo.getLogo() + " §7/gmr <type>");
            }
            return true;
        }
        return true;
    }
}
