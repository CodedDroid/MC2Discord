package me.techiepi.mc2discord.discord.listeners;

import me.techiepi.mc2discord.minecraft.chat.MessageController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class OnMessageReceived implements EventListener {

    MessageController messageController;
    JDA jda;
    String channelId;

    public OnMessageReceived(MessageController messageController){
        this.messageController = messageController;
    }

    @Override
    public void onEvent(@Nonnull GenericEvent event) {
        if(event instanceof MessageReceivedEvent){
            MessageReceivedEvent msgEvent = (MessageReceivedEvent) event;
            if(msgEvent.getChannel().getId().equals(channelId)){
                if(!msgEvent.isWebhookMessage()){
                    sendToMinecraft(msgEvent.getAuthor().getName(), msgEvent.getMessage().getContentRaw());
                }
            }
        }
    }

    public void initializeBot(String token, String channelId) throws LoginException {
        jda = JDABuilder.createDefault(token).addEventListeners(this).build();
        this.channelId = channelId;
    }

    private void sendToMinecraft(String username, String message){
        messageController.sendMessageToMinecraft(username, message);
    }
}
