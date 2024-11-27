# Start with a base image containing Java 1.8 runtime
FROM openjdk:8-jdk-slim

LABEL "org.opencontainers.image.authors"="todimu.isewon"

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

# add the application's jar to the image
COPY target/client-0.0.1-SNAPSHOT.jar /app/client-0.0.1-SNAPSHOT.jar

# Set the working directory (optional but good practice)
WORKDIR /app

# Expose the default port for the application (if needed, adjust to your app's port)
EXPOSE 8090

# execute the application
ENTRYPOINT ["java", "-jar", "client-0.0.1-SNAPSHOT.jar"]
