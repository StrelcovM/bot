FROM openjdk:11
COPY target/telegram-bot.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "-Dlog_level=DEBUG", "app.jar"]