package com.application.gym.dto;

import com.application.gym.Enums.WorkoutType;
import com.application.gym.entity.Workout;

public class WorkoutDto {

    private int workoutId;

    private String workoutName;

    private WorkoutType type;

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

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public static Workout prepareWorkout(WorkoutDto workoutDto) {
        Workout workout = new Workout();
        workout.setWorkoutId(workoutDto.getWorkoutId());
        workout.setWorkoutName(workoutDto.getWorkoutName());
        workout.setType(workoutDto.getType());
        return workout;
    }
}
