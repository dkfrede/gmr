package me.fr3ckzdk.asiii.gmrjava.event;

import me.fr3ckzdk.asiii.gmrjava.GmrJava;
import me.fr3ckzdk.asiii.gmrjava.utils.getBan;
import me.fr3ckzdk.asiii.gmrjava.utils.getLogo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;


public class onJoinEvent implements Listener {

    private GmrJava plugin = GmrJava.getPlugin();

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        if (plugin.getConfig().getBoolean("disable-gmr") == false) {
            if (getBan.getIfBanned(p) == true) {
                if (!p.hasPermission(plugin.getConfig().getString("pass-thru")) || !p.hasPermission(plugin.getConfig().getString("staff-permission"))) {
                    if (plugin.getConfig().getBoolean("warning" + getBan.getWarns(p)) == true) {
                        p.kickPlayer(ChatColor.translateAlternateColorCodes('&', getLogo.getLogo() + " &7Du er banlyst fra GmR med grunden: &6" + getBan.getReason(p) + " &7og har &6" + getBan.getWarns(p) + " &7warnings ansøg på: &6https://discord.gg/QuzdXtCMjs"));
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(plugin.getConfig().getString("staff-permission"))) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getLogo.getLogo() + " &7Spillern &6" + p.getDisplayName() + " &7er bannet med grunden: &6" + getBan.getReason(p) + " og har &6" + getBan.getWarns(p) + " &7warnings"));
                            }
                        }
                    } else {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(plugin.getConfig().getString("staff-permission"))) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getLogo.getLogo() + " &7Spillern &6" + p.getDisplayName() + " &7joinede med warning: &6" + getBan.getWarns(p) + " &7som er tilladt"));
                            }
                        }
                    }
                } else {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission(plugin.getConfig().getString("staff-permission"))) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', getLogo.getLogo() + " &7Spillern &6" + p.getDisplayName() + " &7joinede med en pass-thru permission."));
                        }
                    }
                }
            }
        }
    }

}
