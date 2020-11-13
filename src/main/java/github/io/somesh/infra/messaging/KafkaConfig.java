package github.io.somesh.infra.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * KafkaConfig class.
 * 
 * @author sombose
 */
@Configuration
@EnableKafka
public class KafkaConfig {

  private final ErrorHandler errorHandler;

  /**
   * constructor of KafkaConfig.
   * 
   * @param errorHandler ErrorHandler
   */
  public KafkaConfig(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  /**
   * kafkaTemplate bean.
   * 
   * @param producerFactory ProducerFactory<String, String>
   * @return KafkaTemplate<String, String>
   */
  @Bean
  KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
    KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
    kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
    return kafkaTemplate;
  }

  /**
   * Method of ListenerContainer bean.
   * 
   * @param consumerFactory ConsumerFactory<String, String>
   * @return ConcurrentKafkaListenerContainerFactory<String, String>
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
      ConsumerFactory<String, String> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    // factory.setBatchListener(true);
    factory.setErrorHandler(errorHandler);
    factory.setMessageConverter(new StringJsonMessageConverter());
    return factory;
  }

  /*
   * Additional kafka configuration.
   * 
   *
   * @Bean public ProducerFactory<String, String> producerFactory() { Map<String, Object> config = new HashMap<>();
   * 
   * config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // config.put(ProducerConfig.ACKS_CONFIG,
   * "all"); // config.put(ProducerConfig.RETRIES_CONFIG, 0); // config.put(ProducerConfig.BATCH_SIZE_CONFIG, 1000); //
   * config.put(ProducerConfig.LINGER_MS_CONFIG, 1); config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
   * StringSerializer.class); config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
   * 
   * return new DefaultKafkaProducerFactory(config); }
   * 
   * /*
   * 
   * @Bean public ConsumerFactory<String, String> consumerFactory() { // JsonDeserializer<Container> deserializer = new
   * JsonDeserializer<>(Container.class); // deserializer.setRemoveTypeHeaders(false); //
   * deserializer.addTrustedPackages("*"); // deserializer.setUseTypeMapperForKey(true);
   * 
   * Map<String, Object> config = new HashMap<>();
   * 
   * config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //
   * config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_one"); // config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
   * "earliest"); // config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
   * config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
   * config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
   * 
   * return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new StringDeserializer()); }
   */
}
