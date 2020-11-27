package github.io.somesh.infra.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * DefaultKafkaListenerErrorHandler class.
 * 
 * @author sombose
 *
 */
@Component
@Slf4j
public class DefaultKafkaListenerErrorHandler implements ErrorHandler {

  @Override
  public void handle(Exception thrownException, ConsumerRecord<?, ?> data) {
    log.error("Error while consuming from kafka from Topic: {}, Partition: {}, Offset: {}, TimeStamp {}", data.topic(),
        data.partition(), data.offset(), data.timestamp(), thrownException);

  }

}
