package com.example.level.persistance.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.level.persistance.repository")
@EntityScan(basePackages = "com.example.level.persistance.entity")
public class PersistenceConfiguration {
}
