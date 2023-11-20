FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
COPY app.jar
