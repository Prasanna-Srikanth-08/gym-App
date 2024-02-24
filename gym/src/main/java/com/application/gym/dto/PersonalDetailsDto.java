package com.application.gym.dto;

import com.application.gym.Enums.WorkoutType;
import com.application.gym.entity.Address;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class PersonalDetailsDto {

    private Long id;
    private int personalDetailsId;
    private int age;
    private Long mobileNumber;
    private float weight;
    private float heightInMeters;
    private Address address;

    private WorkoutType workoutType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPersonalDetailsId() {
        return personalDetailsId;
    }

    public void setPersonalDetailsId(int personalDetailsId) {
        this.personalDetailsId = personalDetailsId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeightInMeters() {
        return heightInMeters;
    }

    public void setHeightInMeters(float heightInMeters) {
        this.heightInMeters = heightInMeters;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }
}
