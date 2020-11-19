package github.io.somesh.infra.messaging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * Kafka Configuration Test.
 * 
 * @author sombose
 *
 */
@ExtendWith(MockitoExtension.class)
public class KafkaConfigTest {

  @InjectMocks
  private KafkaConfig underTest;

  @Mock
  private ProducerFactory<String, String> producerFactoryMock;

  @Mock
  private ConsumerFactory<String, String> consumerFactoryMock;

  @Test
  public void shouldCreateKafkaTemplateWithProducerFactory() {
    KafkaTemplate<String, String> kafkaTemplate = underTest.kafkaTemplate(producerFactoryMock);
    assertEquals(kafkaTemplate.getProducerFactory(), producerFactoryMock);
  }

  @Test
  public void shouldCreateKafkaconcurrentListenerWithConsumerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListener =
        underTest.kafkaListenerContainerFactory(consumerFactoryMock);
    assertEquals(kafkaListener.getConsumerFactory(), consumerFactoryMock);

  }
}
