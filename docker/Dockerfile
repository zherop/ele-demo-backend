FROM docker.io/maven:3.5.4-jdk-8
VOLUME /tmp
ADD ele-demo-backend.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar /ele-demo-backend.jar" ]