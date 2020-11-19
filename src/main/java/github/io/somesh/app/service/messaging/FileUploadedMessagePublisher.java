package github.io.somesh.app.service.messaging;

import java.time.Instant;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import github.io.somesh.domain.model.FileStore;
import github.io.somesh.infra.messaging.KafkaMessagePublisher;

/**
 * FileUploadedMessagePublisher publisher.
 * 
 * @author sombose
 *
 */
@Component
public class FileUploadedMessagePublisher implements KafkaMessagePublisher<FileUploadedMesageEvent> {

  private final Environment env;
  private final KafkaTemplate<String, String> kafkaTemplate;

  /**
   * Constructor for FileUploadedMessagePublisher.
   * 
   * @param env Environment
   * @param kafkaTemplate KafkaTemplate<String,String>
   */
  public FileUploadedMessagePublisher(Environment env, KafkaTemplate<String, String> kafkaTemplate) {
    this.env = env;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public String getTopic() {
    return env.getProperty("kafka.topic-name");
  }

  @Override
  public KafkaTemplate<String, String> getKafkaTemplate() {
    return kafkaTemplate;
  }

  /**
   * Publishes an FileUploadEntity into Kafka.
   * 
   * @param entity FileStore
   */
  public void publish(FileStore entity) {
    FileUploadedMesageEvent msgEvent = createMessageEvent(entity);
    this.publish(msgEvent.getEventName(), msgEvent);
  }

  /**
   * Create MessageEvent from Entity.
   * 
   * @param entity FileStore
   * @return FileUploadedMesageEvent
   */
  private FileUploadedMesageEvent createMessageEvent(FileStore entity) {
    return FileUploadedMesageEvent.builder().eventDate(Instant.now()).fileLocation(entity.getFileReferenceId())
        .fileName(entity.getFileName()).uploadedBy(entity.getSubmitterEmail()).build();
  }

}
