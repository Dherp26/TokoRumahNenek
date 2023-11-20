FROM eclipse-temurin:16
ARG JAR_FILE=tmp/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080

