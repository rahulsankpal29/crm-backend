# ---------- Stage 1: Build ----------
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .         # Copy everything into /app
WORKDIR /app/crm-backend  # 👈 Go into the actual Maven project folder
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run ----------
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/crm-backend/target/crm-backend-0.0.1-SNAPSHOT.jar crm-backend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "crm-backend.jar"]
