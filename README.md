# test-etl
Extract, transform and load data into NoSQL databases using Java drivers and their mapping frameworks

## Description
The source code is written in Java. It is like a proof of concept to test different drivers and their mapping frameworks used to connect with NoSQL databases from Java.
<br>
A CSV file is extracted and loaded into a string array in memory, then it is transformed to Objects with the correct data types and finally it is loaded into different NoSQL databases.
I am using a dependency injection framework so I can change from one database to the other, just changing dependencies.

## Libraries and dependencies

### 1) Java v8
- [Maven](https://maven.apache.org/)
- [Guice](https://github.com/google/guice)
- [Cassandra driver](http://docs.datastax.com/en/developer/java-driver/2.0/java-driver/whatsNew2.html)
- [MongoDB Driver](http://docs.mongodb.org/ecosystem/drivers/java/)
- [MongoDB Morphia](https://github.com/mongodb/morphia)
- [Java Spark](http://sparkjava.com/)
- [Freemarker](http://freemarker.org/)

### 2) Data Bases:
- [Cassandra dsc V2](http://www.planetcassandra.org/cassandra/)
- [MongoDB V3](https://www.mongodb.com/)

## Example with Cassandra and Solr

### 1 - Download and install DataStax Enterprise
http://www.datastax.com/products/products-index

I will call DSE_INSTALL_LOCATION to the installation folder .

### 2 - Execute Cassandra in Solr mode:
```
cd DSE_INSTALL_LOCATION/bin
./dse cassandra -s
```

### 3 - Load data into Cassandra and Solr:

#### 3.1 - Load data into Cassandra:
- Start cqlsh:
```
cd DSE_INSTALL_LOCATION/bin
./cqlsh
```
- Create and use keyspace:
```
cqlsh> CREATE KEYSPACE testetl WITH REPLICATION =
       {'class':'NetworkTopologyStrategy', 'Solr':1};
cqlsh> USE testetl;
```

- Create table:
```
cqlsh> CREATE TABLE testetl.flights
(
ID int PRIMARY KEY,
YEAR int,
DAY_OF_MONTH int,
FL_DATE timestamp,
AIRLINE_ID int,
CARRIER varchar,
FL_NUM int,
ORIGIN_AIRPORT_ID int,
ORIGIN varchar,
ORIGIN_CITY_NAME varchar,
ORIGIN_STATE_ABR varchar,
DEST varchar,
DEST_CITY_NAME varchar,
DEST_STATE_ABR varchar,
DEP_TIME timestamp,
ARR_TIME timestamp,
ACTUAL_ELAPSED_TIME timestamp,
AIR_TIME timestamp,
DISTANCE int);
```

- Insert CSV file:

Download CSV file from here:

https://www.dropbox.com/s/4vz2noo3h0tl51r/flights_from_pg.csv?dl=0

You can try to use COPY command (copy CSV file in the correct path):
```
copy flights (ID,YEAR,FL_DATE,AIRLINE_ID,CARRIER,FL_NUM,ORIGIN_AIRPORT_ID,ORIGIN,ORIGIN_CITY_NAME,ORIGIN_STATE_ABR,DEST,DEST_CITY_NAME,DEST_STATE_ABR,DEP_TIME,ARR_TIME,ACTUAL_ELAPSED_TIME,AIR_TIME,DISTANCE) from 'DSE_INSTALL_LOCATION/solr_CQLTest/flights_from_pg.csv';
```

I had issues with the COPY command due to the size of the CSV so I decided to develop a Java program that uses Cassandra Java Driver:

- 1) Copy CSV file on the same folder of the project.
- 2) Execute main in WebData.java
- 3) It deploys a small website on port 8082.
- 4) Just click on Cassandra ETL or directly on this link: http://localhost:8082/load-data-cassandra
- 5) It will parse the CSV file and load data on the Cassandra table.

#### 3.2 - Index data in Solr:

Folder **solr_CQLTest** can be found inside [useful-data folder](https://github.com/guillecanizal/test-etl/tree/master/useful-data/solr_CQLTest)

```
cd DSE_INSTALL_LOCATION/solr_CQLTest/

curl http://localhost:8983/solr/resource/testetl.flights/solrconfig.xml --data-binary @solrconfig.xml -H 'Content-type:text/xml; charset=utf-8'

curl http://localhost:8983/solr/resource/testetl.flights/schema.xml --data-binary @schema.xml -H 'Content-type:text/xml; charset=utf-8'

curl "http://localhost:8983/solr/admin/cores?action=CREATE&name=testetl.flights"

```

### 4 - Query data via Solr API (Examples)

- List all flights leaving a particular airport, sorted by time

Example for airport origin_airport_id = 12954:

http://localhost:8983/solr/testetl.flights/select?q=origin_airport_id%3A12954&sort=dep_time+asc&rows=100&fl=fl_num%2Cdep_time&wt=json&indent=true

- Get the carrier, origin and destination airport based on 10 minute buckets of air_time.

http://localhost:8983/solr/testetl.flights/select?q=*%3A*&wt=json&indent=true&indent=true%20&group=true%20&fl=air_time%2Ccarrier%2Corigin%2Cdest%20&group.func=rint(div(ms(air_time),mul(10,mul(60,1000))))%20&group.limit=2&sort=air_time%20asc

I have also developed a basic website to execute this queries with different params, it connects with Solr API Rest with HTTP connection via Java:

http://localhost:8082/

