package github.io.somesh.infra.messaging;

import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * KafkaMessagePublisher class.
 * 
 * @author sombose
 *
 * @param <T>
 */
public interface KafkaMessagePublisher<T> {

  Logger LOG = org.slf4j.LoggerFactory.getLogger(KafkaMessagePublisher.class);

  /**
   * returns topic name
   * 
   * @return String
   */
  String getTopic();

  /**
   * returns kafkaTemplate
   * 
   * @return KafkaTemplate<String, String>
   */
  KafkaTemplate<String, String> getKafkaTemplate();

  /**
   * publish method.
   * 
   * @param messageKey String
   * @param event T
   */
  default void publish(String messageKey, T event) {
    final Message<T> message = createMessage(event, messageKey);
    this.getKafkaTemplate().send(message).addCallback(r -> this.onSuccess(r), ex -> this.onFailure(ex, message));
  }

  /**
   * CreasteMessage Payload.
   * 
   * @param event T
   * @param messageKey String
   * @return Message<T>
   */
  default Message<T> createMessage(T event, String messageKey) {
    return MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, this.getTopic())
        .setHeader(KafkaHeaders.MESSAGE_KEY, messageKey).build();
  }

  /**
   * onSucces CallBack.
   * 
   * @param result SendResult<String, String>
   */
  default void onSuccess(SendResult<String, String> result) {
    LOG.info("Sucessfully sent record with offset" + result.getRecordMetadata().offset());
  }

  /**
   * onFailure CallBack.
   * 
   * @param ex Throwable
   * @param message Message<T>
   */
  default void onFailure(Throwable ex, Message<T> message) {
    LOG.error("Error publishing{} on kafka Topic: {}, ", message.getPayload().getClass().getSimpleName(),
        this.getTopic(), ex);
  }
}
