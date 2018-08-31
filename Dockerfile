FROM docker.io/maven:3.5.4-jdk-8
VOLUME /tmp
ADD ele-demo-backend.jar
ENV DOCKER_HOST = "tcp://192.168.137.95:2375"
ENTRYPOINT [ "sh", "-c", "java -jar /ele-demo-backend.jar" ]