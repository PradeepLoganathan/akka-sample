# build stage
FROM maven:3.8.5-openjdk-17 AS build
COPY pom.xml mvnw .mvn/ ./
COPY src ./src
RUN mvn clean package -DskipTests

# runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
