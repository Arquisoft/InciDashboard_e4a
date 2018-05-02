FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk wget maven
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ADD ./ ./project/
ADD ./run.sh .
RUN chmod +x run.sh
EXPOSE 8090
RUN cd /project && mvn package -DskipTests
CMD ./run.sh
