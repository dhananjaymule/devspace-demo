# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Set a working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the Maven build context
# The JAR file is in target/ and its name matches the pom.xml artifactId and version
COPY target/food-ordering-app-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

