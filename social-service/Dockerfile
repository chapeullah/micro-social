FROM openjdk:25-ea-21-slim-bullseye
WORKDIR /app
COPY target/*.jar social-service.jar
ENTRYPOINT ["java", "-jar", "social-service.jar"]