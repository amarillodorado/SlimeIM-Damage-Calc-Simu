FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
COPY . .
RUN mvn install -Pproduction

FROM openjdk:21-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]