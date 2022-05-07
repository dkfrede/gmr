package me.fr3ckzdk.asiii.gmrjava.utils;

import me.fr3ckzdk.asiii.gmrjava.GmrJava;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.io.InputStreamReader;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class getBan {

    private GmrJava plugin = GmrJava.getPlugin();

    public static ArrayList<String> getData() throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/dkfrede/gmr/main/list.gmr");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        url.openStream()
                )
        );

        String inputLine;
        ArrayList<String> lines = new ArrayList<String>();

        while ((inputLine = in.readLine()) != null) {
            lines.add(inputLine);
        }

        return lines;
    }
    public static Boolean getIfBanned(Player p) throws IOException {

        ArrayList<String> data = getData();
        Boolean status = false;

        for (int counter = 0; counter < data.size(); counter++) {
            String[] splits = data.get(counter).split(" - ",3);
            String uuid = String.valueOf(p.getUniqueId());
            uuid = uuid.replaceAll("-", "");
            if (splits[0].contains(uuid)) {
                status = true;
            }
        }
        return status;
    }

    public static int getWarns(Player p) {

        ArrayList<String> data = null;
        try {
            data = getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int status = 0;

        for (int counter = 0; counter < data.size(); counter++) {
            String[] splits = data.get(counter).split(" - ",3);

            String uuid = String.valueOf(p.getUniqueId());
            uuid.toString().replaceAll("-", "");
            uuid = uuid.replaceAll("-", "");

            if (splits[0].contains(uuid)) {
                status = Integer.parseInt(splits[2]);
            }
        }
        return status;

    }

    public static String getReason(Player p) {

        ArrayList<String> data = null;
        try {
            data = getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String status = "none";

        for (int counter = 0; counter < data.size(); counter++) {
            String[] splits = data.get(counter).split(" - ",3);
            String uuid = String.valueOf(p.getUniqueId());
            uuid.toString().replaceAll("-", "");
            uuid = uuid.replaceAll("-", "");
            Bukkit.broadcastMessage(uuid);

            if (splits[0].contains(uuid)) {
                status = splits[1];
            }
        }
        return status;

    }
}
