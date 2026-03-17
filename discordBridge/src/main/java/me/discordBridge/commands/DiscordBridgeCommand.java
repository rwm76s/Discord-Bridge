package me.discordBridge.commands;

import me.discordBridge.discord.DiscordBot;
import net.dv8tion.jda.api.JDA;
import org.bukkit.ChatColor;
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

        if (args.length == 0 || !args[0].equalsIgnoreCase("status")) {
            sender.sendMessage(ChatColor.YELLOW + "/discordbridge status");
            return true;
        }

        sender.sendMessage(ChatColor.AQUA + "DiscordBridge Status");

        JDA jda = bot.getJda();

        if (jda == null) {
            sender.sendMessage(ChatColor.RED + "Bot Connected: false");
            return true;
        }

        boolean connected = jda.getStatus().name().equals("CONNECTED");

        sender.sendMessage(ChatColor.GREEN + "Bot Connected: " + connected);

        if (connected) {
            sender.sendMessage(ChatColor.GREEN + "Bot Username: " +
                    jda.getSelfUser().getName());

            boolean channelExists = jda.getTextChannelById(channelId) != null;

            sender.sendMessage(ChatColor.GREEN + "Discord Channel Found: " + channelExists);
        }

        return true;
    }
}