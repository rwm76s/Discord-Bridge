package me.discordBridge.chat;

import me.discordBridge.discord.WebhookClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class MinecraftChatListener implements Listener {

    private final WebhookClient webhookClient;

    public MinecraftChatListener(WebhookClient webhookClient) {
        this.webhookClient = webhookClient;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        String username = event.getPlayer().getName();
        String message = PlainTextComponentSerializer.plainText().serialize(event.message());

        webhookClient.sendMessage(username, message);
    }
}
