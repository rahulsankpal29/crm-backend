# ---------- Stage 1: Build ----------
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY crm-backend/ .     # ✅ copy contents of crm-backend/ into /app/
RUN ls -la              # ✅ see what got copied (debug)
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run ----------
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/crm-backend-0.0.1-SNAPSHOT.jar crm-backend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "crm-backend.jar"]
