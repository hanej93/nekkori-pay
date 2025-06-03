#FROM openjdk:23-slim-bookworm
FROM eclipse-temurin:23-jdk-alpine
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
#"-Djava.security.egd=file:/dev/./urandom",
#"-Dspring.profiles.active=prod",
ENTRYPOINT ["java", "-jar", "/app.jar"]