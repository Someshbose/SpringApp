package github.io.somesh.app.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Kafka MessagePublisher class.
 * 
 * @author sombose
 */
@Component
public class MessagePublisher {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final Environment env;

  /**
   * constructor for {@link MessagePublisher}
   * 
   * @param kafkaTemplate {@link KafkaTemplate}
   * @param env {@link Environment}
   */
  @Autowired
  public MessagePublisher(KafkaTemplate<String, String> kafkaTemplate, Environment env) {
    // TODO Auto-generated constructor stub
    this.kafkaTemplate = kafkaTemplate;
    this.env = env;
  }

  /**
   * publish Method
   * 
   * @param message String
   */
  @SuppressWarnings("Regexp")
  public void publish(String message) {
    kafkaTemplate.send(env.getProperty("kafka.topic-name"), message)
        .addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

          @Override
          public void onFailure(Throwable ex) {
            // TODO Auto-generated method stub
            System.out.println("error while sending record.");

          }

          @Override
          public void onSuccess(SendResult<String, String> result) {
            // TODO Auto-generated method stub
            System.out.println("sending record with offset" + result.getRecordMetadata().offset());
          }
        });
  }
}
