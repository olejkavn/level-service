package com.example.level.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LevelChangedEvent {

    long userId;
    long newLevel;
    long timestamp;
}
