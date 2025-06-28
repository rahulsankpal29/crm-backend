# ---------- Stage 1: Build the Spring Boot app using Maven ----------
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Package the application without running tests
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the built JAR using OpenJDK ----------
FROM openjdk:17.0.1-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/crm-backend-0.0.1-SNAPSHOT.jar crm-backend.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8081

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "crm-backend.jar"]
