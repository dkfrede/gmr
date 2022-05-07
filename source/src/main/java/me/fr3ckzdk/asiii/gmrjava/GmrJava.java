package me.fr3ckzdk.asiii.gmrjava;

import me.fr3ckzdk.asiii.gmrjava.commands.*;
import me.fr3ckzdk.asiii.gmrjava.event.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class GmrJava extends JavaPlugin {

    private static GmrJava plugin;

    public static FileConfiguration config;
    public static File cfile;

    @Override
    public void onEnable() {

        plugin = this;

        System.out.println("You are on version 1.0");
        System.out.println("Join our discord server https://discord.gg/QuzdXtCMjs");

        getCommand("gmr").setExecutor(new gmr());

        getServer().getPluginManager().registerEvents(new onJoinEvent(),this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        cfile = new File(getDataFolder(), "config.yml");

    }

    public static GmrJava getPlugin() {
        return plugin;
    }
}
