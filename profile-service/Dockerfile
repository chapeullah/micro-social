FROM openjdk:25-ea-21-slim-bullseye
WORKDIR /app
COPY target/*.jar profile-service.jar
ENTRYPOINT ["java", "-jar", "profile-service.jar"]