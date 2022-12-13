package xyz.michelepip.funplugin;

import org.bukkit.ChatColor;

public class C {

    public static String gray = String.valueOf(ChatColor.GRAY);
    public static String darkGray = String.valueOf(ChatColor.DARK_GRAY);
    public static String reset = String.valueOf(ChatColor.RESET);

    public static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
