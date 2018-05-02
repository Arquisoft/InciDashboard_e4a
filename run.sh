wget http://downloads.sourceforge.net/project/hsqldb/hsqldb/hsqldb_2_3/hsqldb-2.3.0.zip
ls
unzip hsqldb-2.3.0.zip
nohup bash -c "cd hsqldb-2.3.0/hsqldb/bin && ./runServer.sh &"
java -jar ../project/target/InciDashboard-0.0.1.jar
