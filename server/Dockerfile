FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml ./pom.xml
COPY server/pom.xml ./server/pom.xml
RUN mvn -f ./server/pom.xml dependency:go-offline -B
COPY server/src ./server/src
RUN mvn -f ./server/pom.xml package -DskipTests
FROM eclipse-temurin:21-jre
COPY --from=build /app/server/target/server-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
