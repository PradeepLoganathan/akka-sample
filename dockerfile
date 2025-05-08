# Build stage
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /build

# 1. Copy only pom and wrapper to cache dependencies
COPY pom.xml mvnw .mvn/ ./
RUN mvn dependency:go-offline -B

# 2. Copy source and build the application
COPY src ./src
RUN mvn clean package -DskipTests -B

# Runtime stage: minimal image for running the JAR
FROM eclipse-temurin:17-jre-jammy

# Create non-root user for security
RUN addgroup --system app && \
    adduser --system --ingroup app app

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /build/target/*.jar app.jar

# Metadata and defaults
ARG APP_JAR=app.jar
LABEL maintainer="Pradeep <you@example.com>" \
      version="1.0" \
      description="Spring Akka Counter Service"

# Expose service port and define healthcheck
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/health || exit 1

# Switch to non-root user
USER app

# Entry point for the application
ENTRYPOINT ["java", "-jar", "app.jar"]
