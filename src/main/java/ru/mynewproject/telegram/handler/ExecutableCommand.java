package ru.mynewproject.telegram.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ExecutableCommand {
    void handle(Update update);

    String getCommand();
}
