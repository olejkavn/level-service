package com.example.level.web.controller;

import com.example.level.service.UserLevelOperations;
import com.example.level.web.model.UpdateUserExperienceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/level-service")
@Slf4j
public class LevelServiceController {
    @Value("${my-awesome-property}")
    private String awesomeProperty;
    private final UserLevelOperations userLevelOperations;

    @GetMapping(value = "/")
    public String hello() {
        log.info("test MESSAGE");
        return "Hello from " + awesomeProperty;
    }

    @GetMapping(value = "/level/{userId}")
    public Long getUserLevel(@PathVariable("userId") Long userId) {
        log.info("get user level:{}", userId);
        return userLevelOperations.getLevel(userId);
    }

    @GetMapping(value = "/experience/{userId}")
    public Long getUserExperience(@PathVariable("userId") Long userId) {
        return userLevelOperations.getExperience(userId);
    }

    @PostMapping(value = "/experience/update", consumes = "application/json")
    public String experienceUpdate(/*@Valid*/ @RequestBody UpdateUserExperienceRequest updateUserExperienceRequest) {
        userLevelOperations.processExperienceUpdate(updateUserExperienceRequest.getUserId(), updateUserExperienceRequest.getExperience());
        return "Level update handled by" + awesomeProperty;
    }
}
