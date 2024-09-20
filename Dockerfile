# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY target/driver-service-0.0.1-SNAPSHOT.jar /app/driver-service.jar

# Expose the port for the driver-service
EXPOSE 9122

# Run the application
ENTRYPOINT ["java", "-jar", "/app/driver-service.jar"]

