package com.example.level.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties("level.config")
public class LevelConfigProperties {
    Map<Long, Long> requiredExp;
}
