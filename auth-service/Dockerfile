FROM openjdk:25-ea-21-slim-bullseye
WORKDIR /app
COPY target/*.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "auth-service.jar"]