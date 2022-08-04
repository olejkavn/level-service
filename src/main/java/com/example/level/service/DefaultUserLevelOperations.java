package com.example.level.service;

import com.example.level.persistance.entity.UserEntity;
import com.example.level.persistance.repository.UserRepository;
import com.example.level.service.exception.UserNotFoundException;
import com.example.level.service.model.LevelChangedEvent;
import com.example.level.service.properties.LevelConfigProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

@AllArgsConstructor
@Slf4j
public class DefaultUserLevelOperations implements UserLevelOperations {

    private final UserRepository userRepository;
    private final LevelConfigProperties levelConfigProperties;
    private final KafkaTemplate<String, LevelChangedEvent> levelChangedEventKafkaTemplate;

    @Override
    @Transactional
    public Long getLevel(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("Requested user not registered yet: %s", userId)))
                .getLevel();
    }

    @Override
    public Long getExperience(Long userId) {
        return userRepository.findUserEntityByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("Requested user not registered yet: %s", userId)))
                .getExp();
    }

    @Override
    @Transactional
    public UserEntity processExperienceUpdate(Long userId, Long experience) {
        UserEntity user = userRepository.findByUserId(userId).orElseGet(() -> UserEntity.builder().userId(userId).build());

        long currentLevel = user.getLevel();
        long updatedExperience = user.getExp() + experience;
        long updatedLevel = getLevelByExp(updatedExperience);

        user.setExp(updatedExperience);
        user.setLevel(updatedLevel);
        long timestamp = System.currentTimeMillis();
        LongStream.range(currentLevel +1, updatedLevel + 1)
                .forEach(it -> {
                    LevelChangedEvent levelChangedEvent = LevelChangedEvent.builder()
                            .userId(userId)
                            .newLevel(it)
                            .timestamp(timestamp)
                            .build();
                    levelChangedEventKafkaTemplate.sendDefault(String.valueOf(userId), levelChangedEvent);
                    log.info("Level changed event was send: \n{} ", levelChangedEvent);
                        }
                );

        log.info("User#{} was updated with {} exp: {}", userId, experience, user);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    private long getLevelByExp(long exp) {
        long level = 1;
        log.info(levelConfigProperties.getRequiredExp().toString());
        for (Map.Entry<Long, Long> entry: levelConfigProperties.getRequiredExp().entrySet()) {
            if (entry.getValue() <= exp) {
                level = entry.getKey();
            } if (entry.getValue() > exp) {
                break;
            }
        }
        return level;
    }
}
