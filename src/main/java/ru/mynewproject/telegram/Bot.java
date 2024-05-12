package ru.mynewproject.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mynewproject.telegram.handler.ExecutableCommand;
import ru.mynewproject.telegram.handler.UnknownCommandHandler;

import java.util.Map;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {
    private final String botUsername;
    private final Map<String, ExecutableCommand> commandMap;

    public Bot(@Value("${bot.token}") String botToken,
               @Value("${bot.username}") String botUsername,
               @Autowired @Qualifier("commandMap") Map<String, ExecutableCommand> commandMap) {
        super(botToken);
        this.botUsername = botUsername;
        this.commandMap = commandMap;
    }

    @Override
    public void onUpdateReceived(Update update) {
        commandMap.getOrDefault(update.getMessage().getText(), new UnknownCommandHandler()).handle(update);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
