package github.io.somesh.infra.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * Message Consumer class.
 * 
 * @author sombose
 */
@Slf4j
@Component
public class MessageConsumer {
  /**
   * method to read message.
   * 
   * @param message String
   */
  @KafkaListener(topics = "sample-topic", id = "consumer-1")
  public void consumeMessage(String message) {
    log.info("Message Consumed is {}", message);
  }
}
