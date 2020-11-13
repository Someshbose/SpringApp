package github.io.somesh.app.service.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * FileStatusMessageEvent Consumer class.
 * 
 * @author sombose
 */
@Slf4j
@Component
public class FileStatusMessageEventListener {
  /**
   * method to read message.
   * 
   * @param messageEvent FileStatusMessageEvent
   */
  @KafkaListener(topics = "filestatus", id = "fileStatusConsumer")
  public void consumeMessage(FileStatusMessageEvent messageEvent) {
    log.info("Message Consumed is {}", messageEvent);
  }
}
