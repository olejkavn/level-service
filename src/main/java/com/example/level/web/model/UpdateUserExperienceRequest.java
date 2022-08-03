package com.example.level.web.model;

import lombok.Value;

@Value
public class UpdateUserExperienceRequest {
    Long userId;
    Long experience;
}
