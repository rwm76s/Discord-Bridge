package me.discordBridge.commands;

import me.discordBridge.discord.DiscordBot;
import net.dv8tion.jda.api.JDA;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class DiscordBridgeCommand implements CommandExecutor {

    private final DiscordBot bot;
    private final String channelId;

    public DiscordBridgeCommand(DiscordBot bot, String channelId) {
        this.bot = bot;
        this.channelId = channelId;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Show the user how to properly call the command
        if (args.length == 0 || !args[0].equalsIgnoreCase("status")) {
            sender.sendMessage(
                    Component.text("Did you mean: '/discordbridge status'?").color(NamedTextColor.YELLOW)
            );
            return true;
        }

        sender.sendMessage(
                Component.text("DiscordBridge Status").color(NamedTextColor.AQUA)
        );

        JDA jda = bot.getJda();

        if (jda == null) {
            sender.sendMessage(
                    Component.text("Bot Connected: False").color(NamedTextColor.RED)
            );
            return true;
        }

        boolean connected = jda.getStatus().name().equals("CONNECTED");

        sender.sendMessage(
                Component.text("Bot Connected: " + connected).color(NamedTextColor.GREEN)
        );

        if (connected) {
            sender.sendMessage(Component.text("Bot Username: " +
                    jda.getSelfUser().getName()).color(NamedTextColor.GREEN)
            );

            boolean channelExists = jda.getTextChannelById(channelId) != null;

            sender.sendMessage(
                    Component.text("Discord Channel Found: " + channelExists).color(NamedTextColor.GREEN)
            );
        }

        return true;
    }
}