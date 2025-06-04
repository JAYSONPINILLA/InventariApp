FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean spring-boot:repackage -DskipTests

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /app/target/inventariapp-0.0.1-SNAPSHOT.jar /api-v1.jar
ENTRYPOINT ["java", "-jar", "/api-v1.jar"]


