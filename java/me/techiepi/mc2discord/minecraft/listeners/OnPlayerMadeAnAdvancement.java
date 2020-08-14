package me.techiepi.mc2discord.minecraft.listeners;

import me.techiepi.mc2discord.discord.webhook.WebhookController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class OnPlayerMadeAnAdvancement implements Listener {
    WebhookController webhookController;
    String ggPfpUrl;

    public OnPlayerMadeAnAdvancement(String ggUrl, WebhookController webhookController){
        this.ggPfpUrl = ggUrl;
        this.webhookController = webhookController;
    }

    @EventHandler
    public void onPlayerSendMessage(PlayerAdvancementDoneEvent event){
        String eventName = event.getAdvancement().getKey().getKey();
        String playerName = event.getPlayer().getName();

        if(!eventName.contains("recipe")){
            sendToDiscord(playerName + " made an advancement", playerName + " made the advancement: " + eventName, ggPfpUrl);
        }

    }

    private void sendToDiscord(String username, String message, String avatar){
        if(webhookController != null){
            webhookController.sendRawMessageToDiscord(username, message, avatar);
        }
    }
}
