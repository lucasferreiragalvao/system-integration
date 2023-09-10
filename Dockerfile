FROM openjdk:11.0.11-jre-slim
WORKDIR /app
COPY target/*.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]