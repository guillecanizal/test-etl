# test-etl
Extract, transform and load data into NoSQL databases using Java drivers and their mapping frameworks

## Description
The source code is written in Java. It is like a proof of concept to test different drivers and their mapping frameworks used to connect with NoSQL databases from Java.
<br>
A CSV file is extracted and loaded into a string array in memory, then it is transformed to Objects with the correct data types and finally it is loaded into different NoSQL databases.

## Libraries and dependencies

### 1) Java v8
- [Maven](https://maven.apache.org/)
- [Guice](https://github.com/google/guice)
- [MongoDB Driver](http://docs.mongodb.org/ecosystem/drivers/java/)
- [MongoDB Morphia](https://github.com/mongodb/morphia)
- [Cassandra Driver and Mapping] (http://docs.datastax.com/en/developer/java-driver/2.0/java-driver/whatsNew2.html)

### 2) Data Bases:
- [MongoDB V3](https://www.mongodb.com/)
- [Cassandra dsc V2](http://www.planetcassandra.org/cassandra/)
