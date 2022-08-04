package com.example.level.service;

import com.example.level.persistance.repository.UserRepository;
import com.example.level.service.model.LevelChangedEvent;
import com.example.level.service.properties.LevelConfigProperties;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties({LevelConfigProperties.class})
public class LevelServiceConfiguration {

    @Bean
    public UserLevelOperations userLevelOperations(UserRepository userRepository, LevelConfigProperties levelConfigProperties, KafkaTemplate<String, LevelChangedEvent> levelChangedEventKafkaTemplate) {
        return new DefaultUserLevelOperations(userRepository, levelConfigProperties, levelChangedEventKafkaTemplate);
    }

    @Bean
    public ProducerFactory<String, LevelChangedEvent> producerFactory(@Value("${spring.kafka.bootstrap-servers}") String bootstrapAddress) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, LevelChangedEvent> levelChangedEventKafkaTemplate(ProducerFactory<String, LevelChangedEvent> producerFactory, @Value("${level.changed.topic}") String topic) {
        KafkaTemplate<String, LevelChangedEvent> levelChangedEventKafkaTemplate = new KafkaTemplate<>(producerFactory);
        levelChangedEventKafkaTemplate.setDefaultTopic(topic);
        return levelChangedEventKafkaTemplate;
    }
}
