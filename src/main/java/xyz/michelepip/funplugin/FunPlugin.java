package xyz.michelepip.funplugin;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.minecraft.extras.MinecraftExceptionHandler;
import cloud.commandframework.minecraft.extras.MinecraftHelp;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.michelepip.funplugin.cmd.HelpCommand;

import java.util.function.Function;

public final class FunPlugin extends JavaPlugin {

    private PaperCommandManager<CommandSender> manager;
    private AnnotationParser<CommandSender> annotationParser;
    private Function<ParserParameters, CommandMeta> commandMetaFunction;
    private static MinecraftHelp<CommandSender> help;
    private final BukkitAudiences audiences = BukkitAudiences.create(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            manager = new PaperCommandManager<>(
                    this,
                    CommandExecutionCoordinator.simpleCoordinator(),
                    Function.identity(),
                    Function.identity()
            );
        } catch (Exception e) {
            getLogger().severe("Unable to initialize the command manager, shutting down.");
            getServer().getPluginManager().disablePlugin(this);
        }

        commandMetaFunction = p ->
                CommandMeta.simple()
                        // This will allow you to decorate commands with descriptions
                        .with(CommandMeta.DESCRIPTION, p.get (StandardParameters.DESCRIPTION, "No description"))
                        .build();

        annotationParser = new AnnotationParser<CommandSender>(
                manager,
                CommandSender.class,
                commandMetaFunction
        );

        new MinecraftExceptionHandler<CommandSender>()
                .withArgumentParsingHandler()
                .withInvalidSenderHandler()
                .withInvalidSyntaxHandler()
                .withNoPermissionHandler()
                .withCommandExecutionHandler()
                .withDecorator(message -> Component.text("").append(Component.space()).append(message))
                .apply(manager, audiences::sender);

        help = new MinecraftHelp<CommandSender>(
                "/fphelp help",
                audiences::sender,
                manager
        );

        annotationParser.parse(new HelpCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void queryHelp(String query, Player recipient) {
        help.queryCommands(query, recipient);
    }
}
