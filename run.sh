wget http://downloads.sourceforge.net/project/hsqldb/hsqldb/hsqldb_2_3/hsqldb-2.3.0.zip
unzip hsqldb-2.3.0.zip
add-apt-repository ppa:ubuntu-wine/ppa
apt-get update
apt-get install wine1.6 winetricks
nohup bash -c "cd hsqldb-2.3.0/hsqldb/bin && wineconsole runServer.bat &"
java -jar ../project/target/InciDashboard-0.0.1.jar
