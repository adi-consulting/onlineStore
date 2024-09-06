FROM maven:3.8-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean  package

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar /app/springjar.jar

cmd java -jar /app/springjar.jar