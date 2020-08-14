package me.techiepi.mc2discord.discord.webhook;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.AllowedMentions;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class WebhookController {
    private WebhookClient webhook;

    public void initializeWebhook(String url){
        webhook = WebhookClient.withUrl(url);
    }

    public void shutdownWebhook(){
        webhook.close();
    }

    public void sendMessageToDiscord(String username, String message, String uuid){
        WebhookMessageBuilder builder = new WebhookMessageBuilder();
        builder.setUsername(username);
        builder.setContent(message);
        builder.setAvatarUrl("https://crafatar.com/avatars/" + uuid);
        builder.setAllowedMentions(AllowedMentions.none());
        webhook.send(builder.build());
    }

    public void sendRawMessageToDiscord(String username, String message, String avataUrl){
        WebhookMessageBuilder builder = new WebhookMessageBuilder();
        builder.setUsername(username);
        builder.setContent(message);
        builder.setAvatarUrl(avataUrl);
        builder.setAllowedMentions(AllowedMentions.none());
        webhook.send(builder.build());
    }
}
