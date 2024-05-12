package ru.mynewproject.telegram.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mynewproject.telegram.handler.ExecutableCommand;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {
    private final List<ExecutableCommand> commands;

    @Bean
    public Map<String, ExecutableCommand> commandMap() {
        return commands.stream().collect(Collectors.toMap(ExecutableCommand::getCommand, Function.identity()));
    }
}
