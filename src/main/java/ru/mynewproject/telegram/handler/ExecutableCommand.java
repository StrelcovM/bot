package ru.mynewproject.telegram.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ExecutableCommand<T> {
    T handle(Update update);

    String getCommand();
}
