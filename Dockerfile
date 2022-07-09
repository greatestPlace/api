FROM ubuntu
WORKDIR workdir
RUN apt-get update
RUN apt-cache search openjdk
RUN apt-get -y install openjdk-17-jdk
ARG JAR_FILE=greatestplaces/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]