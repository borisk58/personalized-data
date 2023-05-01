FROM amazoncorretto:17-alpine

ARG JAR_FILE=target/*.jar
ARG APP_NAME=app.jar

WORKDIR /app

COPY ${JAR_FILE} ${APP_NAME}

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
