FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Xms512m","-Xmx3g","-jar","app.jar"]
