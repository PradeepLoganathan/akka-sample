# Use a Java 17 runtime
FROM eclipse-temurin:17-jre

# Copy the fat JAR (built by Maven) into the image
COPY target/spring-akka-app-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app listens on
EXPOSE 8080

# Launch the JAR
ENTRYPOINT ["java","-jar","/app/app.jar"]
