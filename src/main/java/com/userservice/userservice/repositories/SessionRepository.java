package com.userservice.userservice.repositories;

import com.userservice.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByUser_IdAndToken(Long userId, String token);
}
