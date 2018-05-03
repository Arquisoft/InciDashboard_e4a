FROM maven:3.5-jdk-8-alpine
RUN wget http://apache.rediris.es/kafka/1.1.0/kafka_2.11-1.1.0.tgz
RUN tar -xzf kafka_2.11-1.1.0.tgz
ADD ./ ./project/
ADD ./run.sh .
RUN chmod +x run.sh
EXPOSE 80
RUN cd /project && mvn package -DskipTests
CMD ./run.sh
