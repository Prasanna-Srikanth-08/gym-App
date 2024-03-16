package com.application.gym.repository;

import com.application.gym.entity.Trainer;
import com.application.gym.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    Optional<Trainer> findByEmail(String email);
    Optional<Trainer> findByEmailAndPassword(String email,String password);

    @Query("SELECT t FROM Trainer t JOIN t.trainee u WHERE u.id = ?1")
    public Optional<Trainer> getTrainer(Long id);
}
