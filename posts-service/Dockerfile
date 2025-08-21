FROM openjdk:25-ea-21-slim-bullseye
WORKDIR /app
COPY target/*.jar posts-service.jar
ENTRYPOINT ["java", "-jar", "posts-service.jar"]