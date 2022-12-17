package xyz.michelepip.fp.cmd.msg;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.specifier.Greedy;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.michelepip.fp.C;

import java.util.Map;

public class ReplyCommand {

    private final Map<Player, Player> conversations = MessageCommand.getConversations();
    
    @CommandDescription("Reply to a person who has recently messaged you.")
    @CommandMethod("reply <message>")
    @SuppressWarnings("unused") // All command methods show as unused but are
                                // in fact, used
    public void reply(
            @NotNull Player sender,
            @NotNull @Argument("message") @Greedy String message
            ) {
        if (!conversations.containsKey(sender)) {
            sender.sendMessage(C.prefix + " Nobody has messaged you yet!");
            return;
        }

        Player recipient = conversations.get(sender);
        recipient.sendMessage(C.formatReceivedMessage(message, sender));
        sender.sendMessage(C.formatSentMessage(message, recipient));

        conversations.remove(recipient);
        conversations.put(recipient, sender);
    }
}
