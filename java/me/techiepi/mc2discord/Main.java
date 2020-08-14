package me.techiepi.mc2discord;

import me.techiepi.mc2discord.discord.listeners.OnMessageReceived;
import me.techiepi.mc2discord.discord.webhook.WebhookController;
import me.techiepi.mc2discord.minecraft.chat.MessageController;
import me.techiepi.mc2discord.minecraft.listeners.OnPlayerMadeAnAdvancement;
import me.techiepi.mc2discord.minecraft.listeners.OnPlayerSendMessage;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class Main extends JavaPlugin {

    WebhookController webhookController;
    MessageController messageController;
    OnPlayerSendMessage onPlayerSendMessage;
    OnMessageReceived onMessageReceived;
    OnPlayerMadeAnAdvancement onPlayerMadeAnAdvancement;

    String url;
    String token;
    String channelId;
    String ggPfpUrl;

    @Override
    public void onEnable() {
        token = this.getConfig().getString("DISCORD_BOT_TOKEN");
        url = this.getConfig().getString("WEBHOOK_URL");
        channelId = this.getConfig().getString("CHANNEL_ID");
        ggPfpUrl = this.getConfig().getString("GG_IMAGE");

        webhookController = new WebhookController();
        messageController = new MessageController(this.getServer());
        onPlayerSendMessage = new OnPlayerSendMessage(webhookController);
        onMessageReceived = new OnMessageReceived(messageController);
        onPlayerMadeAnAdvancement = new OnPlayerMadeAnAdvancement(ggPfpUrl ,webhookController);

        try {
            onMessageReceived.initializeBot(token, channelId);
        } catch (LoginException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(onPlayerSendMessage, this);
        getServer().getPluginManager().registerEvents(onPlayerMadeAnAdvancement, this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        webhookController.shutdownWebhook();
        messageController.setServerNull();
    }
}
