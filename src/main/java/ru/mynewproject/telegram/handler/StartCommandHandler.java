package ru.mynewproject.telegram.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class StartCommandHandler implements ExecutableCommand<String> {
    private final static String COMMAND = "/start";

    @Override
    public String handle(Update update) {
        Message message = update.getMessage();
        log.debug("Handled start command from user: {} ({})", message.getChatId(), message.getFrom().getUserName());
        return "Могу подсказать время";
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
