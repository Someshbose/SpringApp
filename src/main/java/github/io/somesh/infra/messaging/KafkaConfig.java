package github.io.somesh.infra.messaging;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * KafkaConfig class.
 * 
 * @author iamso
 */
@Configuration
@EnableKafka
public class KafkaConfig {

  /**
   * kafkaTemplate bean.
   * 
   * @return KafkaTemplate<String, String>
   */
  @Bean
  KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  /**
   * Bean for producerFactory.
   * 
   * @return ProducerFactory<String, String>
   */
  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> config = new HashMap<>();

    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    // config.put(ProducerConfig.ACKS_CONFIG, "all");
    // config.put(ProducerConfig.RETRIES_CONFIG, 0);
    // config.put(ProducerConfig.BATCH_SIZE_CONFIG, 1000);
    // config.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return new DefaultKafkaProducerFactory(config);
  }

  /**
   * Method for consumerFactory bean.
   * 
   * @return ConsumerFactory<String, String>
   */
  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    // JsonDeserializer<Container> deserializer = new JsonDeserializer<>(Container.class);
    // deserializer.setRemoveTypeHeaders(false);
    // deserializer.addTrustedPackages("*");
    // deserializer.setUseTypeMapperForKey(true);

    Map<String, Object> config = new HashMap<>();

    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    // config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_one");
    // config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    // config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new StringDeserializer());
  }

  /**
   * Method of ListenerContainer bean.
   * 
   * @return ConcurrentKafkaListenerContainerFactory<String, String>
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setBatchListener(true);
    return factory;
  }
}
