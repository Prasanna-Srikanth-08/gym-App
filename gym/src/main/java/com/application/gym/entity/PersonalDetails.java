package com.application.gym.entity;

import com.application.gym.Enums.WorkoutType;
import com.application.gym.dto.PersonalDetailsDto;
import jakarta.persistence.*;

@Entity
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int personalDetailsId;
    private int age;
    private Long mobileNumber;
    private float weight;
    private float heightInMeters;

    private WorkoutType workoutType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

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

    public static PersonalDetailsDto preparePersonalDetailsDto(PersonalDetails personalDetails){
        PersonalDetailsDto personalDetailsDto = new PersonalDetailsDto();
        personalDetailsDto.setPersonalDetailsId(personalDetails.getPersonalDetailsId());
        personalDetailsDto.setAge(personalDetails.getAge());
        personalDetailsDto.setAddress(personalDetails.getAddress());
        personalDetailsDto.setWeight(personalDetails.getWeight());
        personalDetailsDto.setHeightInMeters(personalDetails.getHeightInMeters());
        personalDetailsDto.setMobileNumber(personalDetails.getMobileNumber());
        personalDetailsDto.setWorkoutType(personalDetails.getWorkoutType());
        return personalDetailsDto;
    }
}
