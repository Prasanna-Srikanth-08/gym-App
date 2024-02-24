package com.application.gym.entity;

import com.application.gym.Enums.WorkoutType;
import com.application.gym.dto.WorkoutDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int workoutId;

    private String workoutName;

    private WorkoutType type;

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
        this.type = type;
    }

    public static WorkoutDto prepareWorkoutDto(Workout workout) {
        WorkoutDto workoutDto = new WorkoutDto();
        workoutDto.setWorkoutId(workout.getWorkoutId());
        workoutDto.setWorkoutName(workout.getWorkoutName());
        workoutDto.setType(workout.getType());
        return workoutDto;
    }
}
