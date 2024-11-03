package ru.mynewproject.telegram.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class UnknownCommandHandler implements ExecutableCommand<String> {
    @Override
    public String handle(Update update) {
        Message message = update.getMessage();
        log.debug("Handled unknown command from user: {} ({})", message.getChatId(), message.getFrom().getUserName());
        return "Я не понимаю, чего вы хотите от меня блять";
    }

    @Override
    public String getCommand() {
        return "unknown";
    }
}
