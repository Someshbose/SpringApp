package github.io.somesh.app.service.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import github.io.somesh.app.service.FileStoreService;
import lombok.extern.slf4j.Slf4j;

/**
 * FileStatusMessageEvent Consumer class.
 * 
 * @author sombose
 */
@Slf4j
@Component
public class FileStatusMessageEventListener {

  private final FileStoreService service;

  /**
   * FileStoreService Constructor.
   * 
   * @param service FileStoreService
   */
  FileStatusMessageEventListener(FileStoreService service) {
    this.service = service;
  }

  /**
   * method to read message.
   * 
   * @param messageEvent FileStatusMessageEvent
   */
  @KafkaListener(topics = "filestatus", id = "fileStatusConsumer")
  public void consumeMessage(FileStatusMessageEvent messageEvent) {
    log.info("Message Consumed is {}", messageEvent);
    service.updateFileStatus(messageEvent);
  }
}
