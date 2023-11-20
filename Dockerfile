FROM eclipse-temurin:11.0.16_8-jdk-alpine
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
COPY app.jar
