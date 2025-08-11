# Используем OpenJDK 17 (совместим с Spring Boot 3)
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория
WORKDIR /app

# Копируем собранный JAR-файл (предварительно соберите проект: ./gradlew build)
COPY build/libs/*.jar app.jar

# Порт, который будет слушать приложение
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]