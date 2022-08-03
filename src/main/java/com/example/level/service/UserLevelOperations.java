package com.example.level.service;

import lombok.AllArgsConstructor;

public interface UserLevelOperations {
    Long getLevel(Long userId);

    Long getExperience(Long userId);

    void processExperienceUpdate(Long userId, Long experience);
}
