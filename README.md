# Description

Demonstrates how one can integrate [kafka](http://kafka.apache.org/ "apache kafka"), [flink](https://flink.apache.org/ "apache flink") and [cassandra](http://cassandra.apache.org/ "apache cassandra") with [spring data](http://projects.spring.io/spring-data-cassandra/ "Spring data cassandra").
 
Please check the [consumer module](https://github.com/viswanath7/flink-kafka-consumer "Related consumer module") in conjunction with this producer for completion.

----------

## Target architecture

In the target architecture diagram shown below, this module implements kafka flink producer that mocks a web-application producing json events by reading a [bundled CSV file](src/main/resources/dataset/events.csv "bundled CSV file conatining events represented as JSON messages").

![Target architecture](src/main/documentation/target-architecture.png "Target arcgitecture")

----------
