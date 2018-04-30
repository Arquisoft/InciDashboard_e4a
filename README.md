# InciDashboard_e4a

[![Build Status](https://travis-ci.org/Arquisoft/InciDashboard_e4a.svg?branch=master)](https://travis-ci.org/Arquisoft/InciDashboard_e4a)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f2f0d0b009384c8aba7deacb39b7b541)](https://www.codacy.com/app/Llambi/InciDashboard_e4a?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciDashboard_e4a&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/InciDashboard_e4a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciDashboard_e4a)

*Skeleton of InciDashboard_e4a module*

## Authors

* Pablo Amorin Triana (UO237060)
* Hugo Perez Fernandez (UO250708)
* Ivan Casielles Alvarez (UO251063)
* Mirza Ojeda Vieira (UO251443)
* Antonio Payá González(UO251065)

## Descripcion

El objetivo de este proyecto es actuar como modulo de Dashboard para un sistema de incidencias

## Manual de uso

### Crear proyecto en eclipse

1. Clonar el proyecto mediante Git en una carpeta local [InciDashboard_e4a](https://github.com/Arquisoft/InciDashboard_e4a.git).
2. Importar el proyecto clonado a Eclipse.
3. Actualizar dependencias con Maven.

### Ejecucion

1. Arrancar [HSQLDB](https://sourceforge.net/projects/hsqldb/files/hsqldb/hsqldb_2_4/hsqldb-2.4.0.zip/download)
   * Para ello, acceder a la carpeta data/hsqldb/bin y lanzar el runServer.bat (o runServer.sh en caso de Linux/Mac).
2. Tener Apache Kafka. 
   * Instrucciones de instalación y despliegue en https://kafka.apache.org/quickstart.
2. Arrancar Apache Zookeeper desde directorio de Apache-kafka:
   * Mac/Linux: ``bin/zookeeper-server-start.sh config/zookeeper.properties``
   * Windows: ``bin\windows\zookeeper-server-start.bat config\zookeeper.properties``
3. Arrancar Apache Kafka desde directorio de Apache-kafka:
   * Mac/Linux: ``bin/kafka-server-start.sh config/server.properties``
   * Windows: ``bin\windows\kafka-server-start.bat config\server.properties``
4. Para arrancarlo todo y que funcione, se debe ejecutar el siguiente comando, estando situado en la carpeta InciDashboard_e4a:
``mvn spring-boot:run``
5. Para lanzar incidencias: En windows: bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic incidencia

```json
{ "agent":{ 
		"username": "xxxxx",
		"password": "xxxxx",
		"kind": "xxxx"
	},
	"inciName": "xxxxx",
	"location":{	
		"lat": "xxxxx",
		"lon": "xxxxx"
	},
	"tags": "xxxxx",
	"moreInfo": "xxxxx",
	"properties":{
		"propiedad1": "xxxx",
		"propiedad2": "xxxx"
	}
}
```

### Usuarios para acceso

* User: hugo@gmail.com pass: 123456
* User: antonio@gmail.com pass: 123456
* User: pasadores@gmail.com pass: 123456
* User: mirza@gmail.com pass: 123456
* User: ivan@gmail.com pass: 123456


### Pruebas de carga

Para la realización de las pruebas de carga se requiere el uso de [Gatling](https://gatling.io/). 
```shell
gatling.sh -s testGatling.scala
```
