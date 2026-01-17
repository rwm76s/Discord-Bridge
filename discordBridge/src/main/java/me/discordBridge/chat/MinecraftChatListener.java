package me.discordBridge.chat;

import me.discordBridge.discord.WebhookClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MinecraftChatListener implements Listener {

    private final WebhookClient webhookClient;

    public MinecraftChatListener(WebhookClient webhookClient) {
        this.webhookClient = webhookClient;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String username = event.getPlayer().getName();
        String message = event.getMessage();

        webhookClient.sendMessage(username, message);
    }
}
