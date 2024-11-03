package ru.mynewproject.telegram.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class GetTimeCommandHandler implements ExecutableCommand<SendMessage> {
    private final static String COMMAND = "/time";

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();
        log.debug("Handled time command from user:{} ({})", message.getChatId(), message.getFrom().getUserName());

        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime dateTimeInMoscow = ZonedDateTime.now(zoneId);

        answer.setText("Текущее время: " + dateTimeInMoscow.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")));

        return answer;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
