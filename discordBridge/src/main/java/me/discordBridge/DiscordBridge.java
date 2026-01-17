package me.discordBridge;

import me.discordBridge.chat.MinecraftChatListener;
import me.discordBridge.discord.DiscordBot;
import me.discordBridge.discord.WebhookClient;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordBridge extends JavaPlugin {

    private DiscordBot discordBot;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        String token = getConfig().getString("discord.bot-token");
        String channelId = getConfig().getString("discord.channel-id");
        String webhookUrl = getConfig().getString("webhook.url");

        if (token == null || channelId == null) {
            getLogger().severe("Discord config values missing!");
            return;
        }

        discordBot = new DiscordBot();
        discordBot.start(this, token, channelId);

        WebhookClient webhookClient = new WebhookClient(webhookUrl);
        Bukkit.getPluginManager().registerEvents(
                new MinecraftChatListener(webhookClient),
                this
        );

        getLogger().info("DiscordBridge enabled!");
    }

    @Override
    public void onDisable() {
        if (discordBot != null) {
            discordBot.shutdown();
        }
        getLogger().info("DiscordBridge disabled!");
    }
}
