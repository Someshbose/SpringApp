package github.io.somesh.app.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Message Consumer class.
 * 
 * @author sombose
 */
@Component
public class MessageConsumer {
  /**
   * method to read message.
   * 
   * @param message String
   */
  @KafkaListener(topics = "sample-topic", id = "consumer-1")
  @SuppressWarnings("Regexp")
  public void consumeMessage(String message) {
    System.out.println("Message Consumed is" + message);
  }
}
