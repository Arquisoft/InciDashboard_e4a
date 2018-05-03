cd kafka_2.11-1.1.0
nohup bin/zookeeper-server-start.sh config/zookeeper.properties &
echo "Zookeeper started"
nohup bin/kafka-server-start.sh config/server.properties &
echo "Kafka started"
sleep 10
cd ../project/target
java -jar InciDashboard-0.0.1.jar
