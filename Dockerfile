FROM docker.io/maven:3.5.4-jdk-8
ENV DOCKER_HOST = "tcp://192.168.137.95:2375"
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8800
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]