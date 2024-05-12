package ru.mynewproject.telegram.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class GetTimeCommandHandler implements ExecutableCommand {
    private final static String COMMAND = "/time";

    @Override
    public void handle(Update update) {
        Message message = update.getMessage();
        log.debug("Handled time command from user:{} ({})", message.getChatId(), message.getFrom().getUserName());
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
