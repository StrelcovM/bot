package ru.mynewproject.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mynewproject.telegram.handler.ExecutableCommand;
import ru.mynewproject.telegram.handler.UnknownCommandHandler;

import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {
    private final String botUsername;
    private final Map<String, ExecutableCommand<?>> commandMap;
    private final UnknownCommandHandler unknownCommandHandler;

    @Autowired
    public Bot(@Value("${bot.token}") String botToken,
               @Value("${bot.username}") String botUsername,
               @Qualifier("commandMap") Map<String, ExecutableCommand<?>> commandMap,
               UnknownCommandHandler unknownCommandHandler) {
        super(botToken);
        this.botUsername = botUsername;
        this.commandMap = commandMap;
        this.unknownCommandHandler = unknownCommandHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Object commandResult;
        commandResult = commandMap.getOrDefault(update.getMessage().getText(), unknownCommandHandler).handle(update);
        try {
            if (Objects.isNull(commandResult)) {
                log.debug("Command without result");
            } else if (commandResult instanceof String) {
                SendMessage answer = new SendMessage(update.getMessage().getChatId().toString(), (String) commandResult);

                execute(answer);
            } else if (commandResult instanceof SendMessage) {
                execute((SendMessage) commandResult);
            }
        } catch (TelegramApiException e) {
            log.error("Error sending commandResult", e);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
