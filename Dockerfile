# ==========================================
# Stage 1: Build the Spring Boot Application
# ==========================================
FROM registry.access.redhat.com/ubi8/openjdk-17:latest AS builder

# Switch to root to avoid permission issues when creating the /app directory
USER root

WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src src

# Build the application
RUN mvn clean package -DskipTests

# ==========================================
# Stage 2: Create the Lightweight Runtime Image
# ==========================================
FROM registry.access.redhat.com/ubi8/openjdk-17-runtime:latest

WORKDIR /deployments

# Copy the compiled JAR file from the builder stage
COPY --from=builder /app/target/*.jar /deployments/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/deployments/app.jar"]  



