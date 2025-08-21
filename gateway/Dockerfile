FROM openjdk:25-ea-21-slim-bullseye
WORKDIR /app
COPY target/*.jar gateway.jar
ENTRYPOINT ["java", "--enable-native-access=ALL-UNNAMED", "-jar", "gateway.jar"]