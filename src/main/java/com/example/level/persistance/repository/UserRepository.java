package com.example.level.persistance.repository;

import com.example.level.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UserEntity> findByUserId(Long userId);

    Optional<UserEntity> findUserEntityByUserId(Long userId);
}
