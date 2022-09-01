FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
ARG JAR_FILE=target/crud-0.0.1-SNAPSHOT.jar
ARG JSON_FILE=output.json
ADD ${JAR_FILE} app.jar
ADD ${JSON_FILE} output.json
ENTRYPOINT ["java","-jar","/app.jar"]

## docker build -t demo .
## docker run -it -p 8080:8080 demo .
