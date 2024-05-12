package ru.mynewproject.telegram.handler;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class UnknownCommandHandler implements ExecutableCommand {
    @Override
    public void handle(Update update) {
        Message message = update.getMessage();
        log.debug("Handled unknown command from user: {} ({})", message.getChatId(), message.getFrom().getUserName());
    }

    @Override
    public String getCommand() {
        return null;
    }
}
