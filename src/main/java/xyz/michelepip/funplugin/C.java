package xyz.michelepip.funplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class C {

    public static String gray = String.valueOf(ChatColor.GRAY);
    public static String darkGray = String.valueOf(ChatColor.DARK_GRAY);
    public static String reset = String.valueOf(ChatColor.RESET);

    public static String prefix = gray + "[" + darkGray + "FP" + gray + "]";

    public static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String formatReceivedMessage(String msg, Player sender) {
        return C.prefix + " " + sender.getName() + " --> You: " + C.darkGray + msg;
    }

    public static String formatSentMessage(String msg, Player recipient) {
        return C.prefix + " " + "You --> " + recipient.getName() + ": " + C.darkGray + msg;
    }
}
