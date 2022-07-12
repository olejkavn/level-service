package com.example.level.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level-service")
public class LevelServiceController {
    @Value("${my-awesome-property}")
    private String awesomeProperty;
//    private final UserLevelOperations userLevelOperations;

    @GetMapping(value = "/")
    public String hello() {
        return "Hello from " + awesomeProperty;
    }

//    @GetMapping(value = "/level/{userId}")
//    public Long getUserLevel(@PathVariable("userId") Long userId) {
//        return userLevelOperations.getLevel(userId);
//    }
//
//    @GetMapping(value = "/experience/{userId}")
//    public Long getUserExperience(@PathVariable("userId") Long userId) {
//        return userLevelOperations.getExperience(userId);
//    }
//
//    @PostMapping(value = "/experience/update", consumes = "application/json")
//    public String experienceUpdate(@Valid @RequestBody UpdateUserExperienceRequest updateUserExperienceRequest) {
//        userLevelOperations.processExperienceUpdate(updateUserExperienceRequest.getUserId(), updateUserExperienceRequest.getDeltaExperience());
//        return "Level update handled by" + awesomeProperty;
//    }
}
