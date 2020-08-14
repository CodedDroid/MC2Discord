package me.techiepi.mc2discord.minecraft.listeners;

import me.techiepi.mc2discord.discord.webhook.WebhookController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPlayerSendMessage implements Listener {

    WebhookController webhookController;

    public OnPlayerSendMessage(WebhookController webhookController){
        this.webhookController = webhookController;
    }

    @EventHandler
    public void onPlayerSendMessage(AsyncPlayerChatEvent event){
        sendToDiscord(event.getPlayer().getName(), event.getMessage(), event.getPlayer().getUniqueId().toString());
    }

    private void sendToDiscord(String username, String message, String uuid){
        webhookController.sendMessageToDiscord(username, message, uuid);
    }
}
