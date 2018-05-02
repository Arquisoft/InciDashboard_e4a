wget http://downloads.sourceforge.net/project/hsqldb/hsqldb/hsqldb_2_3/hsqldb-2.3.0.zip
unzip hsqldb-2.3.0.zip
nohup bash -c "cd hsqldb-2.3.0/hsqldb/bin && java -cp ../lib/hsqldb.jar org.hsqldb.server.Server &"
java -jar ../project/target/InciDashboard-0.0.1.jar
