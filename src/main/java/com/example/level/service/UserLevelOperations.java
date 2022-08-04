package com.example.level.service;

import com.example.level.persistance.entity.UserEntity;

import java.util.List;

public interface UserLevelOperations {
    Long getLevel(Long userId);

    Long getExperience(Long userId);

    UserEntity processExperienceUpdate(Long userId, Long experience);

    List<UserEntity> getAllUsers();

    void deleteAllUsers();
}
