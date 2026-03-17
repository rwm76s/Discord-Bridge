package me.discordBridge.discord;

import me.discordBridge.chat.DiscordMessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordBot {

    private JDA jda;

    public JDA getJda()
    {
        return jda;
    }

    public boolean start(JavaPlugin plugin, String token, String channelId) {
        try {
            jda = JDABuilder.createDefault(token)
                    .enableIntents(
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.MESSAGE_CONTENT
                    )
                    .addEventListeners(new DiscordMessageListener(channelId))
                    .build();

            plugin.getLogger().info("Connecting to Discord...");

            // Wait until connection is found
            jda.awaitReady();

            plugin.getLogger().info("Discord bot connected as " + jda.getSelfUser().getName());

            // Validate the channel
            if(jda.getTextChannelById(channelId) == null) {
                plugin.getLogger().severe("Invalid Discord channel ID!");
                return false;
            }

            plugin.getLogger().info("Discord channel verified!");

            return true;

        } catch (Exception e) {
            plugin.getLogger().severe("Failed to start Discord bot!");
            e.printStackTrace();
            return false;
        }
    }

    public void shutdown() {
        if (jda != null) {
            jda.shutdownNow();
        }
    }
 }
