package xyz.michelepip.funplugin.cmd.msg;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.specifier.Greedy;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.michelepip.funplugin.C;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class MessageCommand {
    private static final Map<Player, Player> conversations = new HashMap<>();

    @CommandPermission("fp.msg")
    @CommandDescription("Privately message a player.")
    @CommandMethod("msg|fpmsg <player> <msg>")
    public void msg(
            @NotNull Player sender,
            @NotNull @Argument("player") Player recipient,
            @NotNull @Argument("msg") @Greedy String message
    ) {
        sender.sendMessage(C.formatSentMessage(message, recipient));
        recipient.sendMessage(C.formatReceivedMessage(message, sender));

        conversations.remove(recipient);
        conversations.put(recipient, sender);
    }

    public static boolean playerMessaged(Player p) {
        return conversations.containsKey(p);
    }

    public static void remove(Player p) {
        conversations.remove(p);
    }

    public static void put(Player p, Player x) {
        conversations.put(p, x);
    }

    public static Map<Player, Player> getConversations() {
        return conversations;
    }
}
