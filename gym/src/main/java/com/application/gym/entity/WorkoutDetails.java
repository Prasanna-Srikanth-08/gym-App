package com.application.gym.entity;

import com.application.gym.Enums.WorkoutType;

import java.util.Date;

public class WorkoutDetails {
    private Date workoutDate;
    private String workoutName;
    private WorkoutType type;

    public Date getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Date workoutDate) {
        this.workoutDate = workoutDate;
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
}
