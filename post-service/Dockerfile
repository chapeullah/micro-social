FROM openjdk:25-ea-21-slim-bullseye
WORKDIR /app
COPY target/*.jar post-service.jar
ENTRYPOINT ["java", "-jar", "post-service.jar"]