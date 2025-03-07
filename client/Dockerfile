FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml ./pom.xml
COPY client/pom.xml ./client/pom.xml
RUN mvn -f ./client/pom.xml dependency:go-offline -B
COPY client/src ./client/src
RUN mvn -f ./client/pom.xml package -DskipTests
FROM eclipse-temurin:21-jre
COPY --from=build /app/client/target/client-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
