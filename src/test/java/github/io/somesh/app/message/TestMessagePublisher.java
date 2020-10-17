package github.io.somesh.app.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

/**
 * 
 * @author iamso
 *
 */
@ExtendWith(SpringExtension.class)
public class TestMessagePublisher {

  private static final String TOPIC_NAME = "kafka.topic-name";
  private static final String topicName = "sample-topic";
  private static final String message = "sample";

  @InjectMocks
  private MessagePublisher publisher;

  @Mock
  private KafkaTemplate<String, String> template;

  @Mock
  Environment env;

  /**
   * 
   */
  @Test
  public void testPublish() {
    ArgumentCaptor<Message<String>> captor = ArgumentCaptor.forClass(Message.class);
    ListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<SendResult<String, String>>();

    Mockito.when(env.getProperty(TOPIC_NAME)).thenReturn(topicName);
    Mockito.when(template.send(topicName, message)).thenReturn(future);
    publisher.publish(message);
    // Mockito.verify(template).send("sample-topic",captor.capture());
    // assertNotNull(captor);
    Mockito.verify(template).send(topicName, message);
  }
}
