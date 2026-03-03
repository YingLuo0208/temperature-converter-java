# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first to leverage Docker cache
COPY pom.xml .

# Copy the entire project
COPY . /app

# Build the application and run tests
RUN mvn clean package

# Stage 2: Create the runtime image (optional: for smaller image size)
# FROM eclipse-temurin:17-jre-alpine
# WORKDIR /app
# COPY --from=build /app/target/temperature-converter.jar .

# Run the application
CMD ["java", "-jar", "target/temperature-converter.jar"]

