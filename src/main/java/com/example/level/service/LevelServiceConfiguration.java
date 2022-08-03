package com.example.level.service;

import com.example.level.persistance.repository.UserRepository;
import com.example.level.service.properties.LevelConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({LevelConfigProperties.class})
public class LevelServiceConfiguration {
    @Bean
    public UserLevelOperations userLevelOperations(UserRepository userRepository, LevelConfigProperties levelConfigProperties) {
        return new DefaultUserLevelOperations(userRepository, levelConfigProperties);
    }
}
