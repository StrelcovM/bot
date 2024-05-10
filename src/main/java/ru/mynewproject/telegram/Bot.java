package ru.mynewproject.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {
    private final String greeting;
    private final String botUsername;

    public Bot(@Value("${bot.token}") String botToken,
               @Value("${bot.username}") String botUsername,
               @Value("${bot.greeting}") String greeting) {
        super(botToken);
        this.botUsername = botUsername;
        this.greeting = greeting;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Thread thread = new Thread(() -> {
            Message message = update.getMessage();
            log.debug("Have new message from {}({}): {}", update.getMessage().getFrom().getUserName(), message.getChatId(), update.getMessage().getText());
            try {
                SendMessage sendMessage = new SendMessage(message.getChatId().toString(), String.format(greeting, message.getFrom().getFirstName()));
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Error while sending message", e);
            }
        });
        thread.setName(Thread.currentThread().getName() + "-" + Thread.activeCount());
        thread.start();
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
