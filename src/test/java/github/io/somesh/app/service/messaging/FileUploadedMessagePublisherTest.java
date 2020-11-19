package github.io.somesh.app.service.messaging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;
import github.io.somesh.domain.model.FileStore;

/**
 * FileUploadedMessagePublisher Junit file.
 * 
 * @author sombose
 */
@ExtendWith(MockitoExtension.class)
public class FileUploadedMessagePublisherTest {

  @InjectMocks
  private FileUploadedMessagePublisher publisher;

  @Mock
  private Environment env;

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @Test
  public void testPublish() {

    ArgumentCaptor<Message<FileUploadedMesageEvent>> captor = ArgumentCaptor.forClass(Message.class);
    FileStore fileEntity =
        new FileStore.Builder().fileTypeCode("TEST-TYPE").fileContent("ASA").fileName("Sample.txt").build();

    ListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<>();
    Mockito.when(kafkaTemplate.send(Mockito.any(Message.class))).thenReturn(future);
    publisher.publish(fileEntity);
    Mockito.verify(kafkaTemplate).send(captor.capture());
    assertNotNull(captor);
    Message<FileUploadedMesageEvent> capturedMessage = captor.getValue();
    assertEquals("Sample.txt", capturedMessage.getPayload().getFileName());
  }
}
