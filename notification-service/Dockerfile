FROM openjdk:25-ea-21-slim-bullseye
WORKDIR /app
COPY target/*.jar notification-service.jar
ENTRYPOINT ["java", "-jar", "notification-service.jar"]