package xyz.michelepip.funplugin.cmd.msg;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.specifier.Greedy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.michelepip.funplugin.C;

public class MessageCommand {

    @CommandPermission("fp.msg")
    @CommandDescription("Privately message a player.")
    @CommandMethod("msg|fpmsg <player> <msg>")
    public void msg(
            @NotNull Player sender,
            @NotNull @Argument("player") Player recipient,
            @NotNull @Argument("msg") @Greedy String message
    ) {
        sender.sendMessage(C.gray + "[" + C.darkGray + "FP" + C.gray + "]"
                            + " You --> " + recipient.getName() + ": " + C.darkGray + message);
        recipient.sendMessage(C.gray + "[" + C.darkGray + "FP" + C.gray + "]"
                 + " " + sender.getName() + " --> You: " + C.darkGray + message);
    }
}
