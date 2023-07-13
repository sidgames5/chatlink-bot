package com.sidgames5.chatlink.bot.event;

import com.sidgames5.chatlink.bot.Bot;
import com.sidgames5.chatlink.bot.MessageUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEvents extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MessageEvents.class);
    public void onMessageReceived(MessageReceivedEvent event) {
        String sender = event.getMessage().getAuthor().getName();
        String message = event.getMessage().getContentRaw();
        //logger.info("[DISCORD: " + sender + "] " + message);
        if (!event.getMessage().getAuthor().isBot() && event.isFromGuild() && event.getMessage().getChannel().getId().equals(Bot.getChannelID())) Bot.getMcMessageSender().sendMessage("[DISCORD: " + sender + "] " + MessageUtil.replaceIDwithUsername(message));
    }
    public void onMessageUpdate(MessageUpdateEvent event) {
        String sender = event.getMessage().getAuthor().getName();
        String message = event.getMessage().getContentRaw();
        //logger.info("[DISCORD: " + sender + ", Edited] " + message);
        if (!event.getMessage().getAuthor().isBot() && event.isFromGuild() && event.getMessage().getChannel().getId().equals(Bot.getChannelID())) Bot.getMcMessageSender().sendMessage("[DISCORD: " + sender + ", Edited] " + MessageUtil.replaceIDwithUsername(message));
    }
}
