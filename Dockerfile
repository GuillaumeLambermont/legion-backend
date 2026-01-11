# Stage 1: Runtime
FROM eclipse-temurin:25-jre-noble

# Create a non-root user for better security in your homelab
RUN useradd -ms /bin/bash springuser
USER springuser

WORKDIR /app

# Copy the jar from your target folder
COPY target/*.jar app.jar

# Expose Spring Boot's default port
EXPOSE 8080

# Run the application with optimized memmory settings
ENTRYPOINT ["java", "-Xmx512m", "-jar", "app.jar"]
