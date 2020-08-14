package me.techiepi.mc2discord.minecraft.chat;

import org.bukkit.ChatColor;
import org.bukkit.Server;

public class MessageController {
    Server server;

    public MessageController(Server server){
        this.server = server;
    }

    public void sendMessageToMinecraft(String username, String message){
        String formattedMessage = "<Discord - " + username + "> " + message;
        server.broadcastMessage(ChatColor.GREEN + formattedMessage);
    }

    public void setServerNull(){
        this.server = null;
    }
}
