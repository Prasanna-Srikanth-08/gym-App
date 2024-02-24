package com.application.gym.repository;

import com.application.gym.Enums.WorkoutType;
import com.application.gym.dto.UserDto;
import com.application.gym.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
    //List<User> findByTrainerTrainerId(Long trainerId);
    List<User> findByPersonalDetailsWorkoutType(WorkoutType workoutType);
}
