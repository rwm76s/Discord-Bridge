# DiscordBridge

A lightweight Minecraft–Discord chat bridge built for **Paper 1.21.x** that synchronizes chat between a Minecraft server and a Discord channel.

This project uses a **hybrid approach**:

* **Discord Bot (JDA)** for receiving messages from Discord → Minecraft
* **Discord Webhook** for sending messages from Minecraft → Discord

This design keeps the system simple, efficient, and avoids common message loop issues. (I also just wanted an excuse to mess with webhooks)

---

## Features

*  Discord → Minecraft chat relay
*  Minecraft → Discord chat relay (via webhook)
*  Channel-specific Discord listening
*  Bot & webhook loop prevention
*  Asynchronous HTTP (no server lag)
*  Clean, extensible architecture

---

## Requirements

* **Minecraft Server**: Paper 1.21.x
* **Java**: Java 21
* **Build Tool**: Maven
* **Discord**:

  * A Discord bot application
  * A Discord webhook

---

## Project Structure as of this README

```
DiscordBridge/
├─ src/main/java/me/riley/discordbridge/
│  ├─ DiscordBridge.java          # Main plugin class
│  ├─ chat/
│  │  ├─ DiscordMessageListener.java
│  │  └─ MinecraftChatListener.java
|  ├─ commands/
|  |  └─ DiscordBridgeCommand.java
│  └─ discord/
│     ├─ DiscordBot.java
│     └─ WebhookClient.java
├─ src/main/resources/
│  ├─ plugin.yml
│  └─ config.yml
└─ pom.xml
```

---

## Configuration

After first launch, edit:

```
/plugins/DiscordBridge/config.yml
```

```yaml
discord:
  bot-token: "YOUR_DISCORD_BOT_TOKEN"
  channel-id: "YOUR_DISCORD_CHANNEL_ID"

webhook:
  url: "YOUR_DISCORD_WEBHOOK_URL"
```

 **Never commit your bot token or webhook URL to a public repository.**

---

## Installation

1. Clone the repository
2. Open the project in **IntelliJ IDEA**
3. Ensure **Java 21** is configured
4. Build the plugin:

   ```
   Maven → clean → package
   ```
5. Copy the generated JAR from:

   ```
   target/DiscordBridge-1.0-SNAPSHOT.jar
   ```

   into your server’s:

   ```
   /plugins
   ```
6. Start the server
7. Configure `config.yml`
8. Restart the server

---

## Testing

* Messages sent in the configured Discord channel appear in Minecraft chat
* Messages sent in Minecraft chat appear in Discord via webhook

---

## Commands

* To use the **status** command, type "/discordbridge status" into the chat
* The output will notify you if either the webhook or discord bot are not connected

---

## Built With

* [PaperMC](https://papermc.io/)
* [JDA (Java Discord API)](https://github.com/DV8FromTheWorld/JDA)
* [Gson](https://github.com/google/gson)
* Maven Shade Plugin

---

## Security Notes

* Do **not** expose your bot token
* Regenerate the token immediately if leaked
* Restrict the bot to only necessary permissions (View channels, read messages, and send messages)
