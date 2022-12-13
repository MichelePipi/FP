package xyz.michelepip.funplugin.cmd;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.michelepip.funplugin.FunPlugin;

public class HelpCommand {

    @CommandMethod("fphelp <query>")
    @CommandDescription("Description of commands for FunPlugin.")
    public void help(
            @NotNull Player sender,
            @NotNull @Argument("query") String query) {
        FunPlugin.queryHelp(query, sender);
    }
}
