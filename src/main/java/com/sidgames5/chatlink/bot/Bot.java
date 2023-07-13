package com.sidgames5.chatlink.bot;

import com.sidgames5.chatlink.bot.event.MessageEvents;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.IntConsumer;

public class Bot {
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    public static JDA bot;

    private static String webhookURL;
    private static MinecraftMessageSender mcMessageSender;
    private static String channelID;

    public static void configure(String webhookURL, MinecraftMessageSender mcMessageSender, String channelID) {
        Bot.webhookURL = webhookURL;
        Bot.mcMessageSender = mcMessageSender;
        Bot.channelID = channelID;
    }

    public static MinecraftMessageSender getMcMessageSender() {
        return mcMessageSender;
    }

    public static String getChannelID() {
        return channelID;
    }

    public static void run(String token) throws LoginException {
        logger.info("Starting bot");

        Collection<GatewayIntent> intents = new ArrayList<>();
        intents.add(GatewayIntent.GUILD_MEMBERS);
        intents.add(GatewayIntent.GUILD_MESSAGES);

        bot = JDABuilder.create(token, intents)
                .setActivity(Activity.watching("chat"))
                .build();

        bot.addEventListener(new MessageEvents());

        logger.info("Bot started!");
    }

    public static void stop() {
        bot.shutdown();
    }

    public static void sendToDiscord(User sender, String message) throws IOException {
        String imageURL = "https://crafatar.com/renders/head/" + sender.getUniqueId() + "?overlay";

        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.setUsername(sender.getName());
        webhook.setContent(message);
        webhook.setAvatarUrl(imageURL);
        webhook.execute();
    }
    public static void sendToDiscord(String sender, String message) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.setUsername(sender);
        webhook.setContent(message);
        webhook.execute();
    }
}
