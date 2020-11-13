# kafka- commands

- Download kafka from the [link](https://kafka.apache.org/downloads)
Unzip and keep it in c folder.
 
- To start kafka server. start zookeeper
```sh
cd c:\kafka
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```
- Start the kafka server now.
```sh
bin\windows\kafka-server-start.bat config\server.properties
```

- Inspecting message on kafka topic

```sh
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic <topic-name> --from-beginning
```