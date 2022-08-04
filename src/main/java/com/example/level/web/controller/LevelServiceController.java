package com.example.level.web.controller;

import com.example.level.persistance.entity.UserEntity;
import com.example.level.service.UserLevelOperations;
import com.example.level.web.model.UpdateUserExperienceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LevelServiceController {
    @Value("${my-awesome-property}")
    private String awesomeProperty;
    private final UserLevelOperations userLevelOperations;

    @GetMapping(value = "/")
    public String hello() {
        return "Hello from " + awesomeProperty;
    }

    @GetMapping(value = "/level/{userId}")
    public Long getUserLevel(@PathVariable("userId") Long userId) {
        log.info("get user level: {}", userId);
        return userLevelOperations.getLevel(userId);
    }

    @GetMapping(value = "/experience/{userId}")
    public Long getUserExperience(@PathVariable("userId") Long userId) {
        log.info("get user experience: {}", userId);
        return userLevelOperations.getExperience(userId);
    }

    @GetMapping(value = "/users")
    public List<UserEntity> getAllUsers() {
        log.info("get user all users");
        return userLevelOperations.getAllUsers();
    }

    @DeleteMapping(value = "/users/delete")
    public void deleteAllUsers() {
        log.info("remove all users");
        userLevelOperations.deleteAllUsers();
    }

    @PostMapping(value = "/experience/update", consumes = "application/json")
    public UserEntity experienceUpdate(/*@Valid*/ @RequestBody UpdateUserExperienceRequest updateUserExperienceRequest) {
        log.info("Update user: {} experience with: {} experience value", updateUserExperienceRequest.getUserId(), updateUserExperienceRequest.getExperience());
        return userLevelOperations.processExperienceUpdate(updateUserExperienceRequest.getUserId(), updateUserExperienceRequest.getExperience());
    }
}
