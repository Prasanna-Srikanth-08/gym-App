package com.application.gym.repository;

import com.application.gym.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    Optional<Trainer> findByEmail(String email);
    Optional<Trainer> findByEmailAndPassword(String email,String password);
}
