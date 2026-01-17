package me.discordBridge.discord;

import me.discordBridge.chat.DiscordMessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordBot {

    private JDA jda;

    public void start(JavaPlugin plugin, String token, String channelId) {
        try {
            jda = JDABuilder.createDefault(token)
                    .enableIntents(
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.MESSAGE_CONTENT
                    )
                    .addEventListeners(new DiscordMessageListener(channelId))
                    .build();

            plugin.getLogger().info("Discord bot logging in...");
        } catch (Exception e) {
            plugin.getLogger().severe("Failed to start Discord bot!");
            e.printStackTrace();
        }
    }

    public void shutdown() {
        if (jda != null) {
            jda.shutdownNow();
        }
    }
}
