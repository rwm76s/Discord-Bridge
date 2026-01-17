package me.discordBridge.chat;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordMessageListener extends ListenerAdapter {

    private final String channelId;

    public DiscordMessageListener(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Ignore bots (includes webhooks)
        if (event.getAuthor().isBot()) return;

        // Only listen to one channel
        if (!event.getChannel().getId().equals(channelId)) return;

        // Extract the first character of username and convert to uppercase if letter
        String firstLetter = event.getAuthor().getName().substring(0, 1).toUpperCase();

        // Concatenate with the rest of the string
        String remainingLetters = event.getAuthor().getName().substring(1);
        String username = firstLetter + remainingLetters;

        String content = event.getMessage().getContentDisplay();

        if (content.isBlank()) return;

        // Send to Minecraft main thread
        Bukkit.getScheduler().runTask(
                Bukkit.getPluginManager().getPlugin("DiscordBridge"),
                () -> Bukkit.broadcastMessage(
                        "§9[Discord] §f" + username + ": " + content
                )
        );
    }
}
