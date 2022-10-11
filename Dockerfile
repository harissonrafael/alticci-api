from openjdk:12-alpine
COPY ./build/libs/app.jar /app/alticii.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "alticii.jar"]
EXPOSE 8080