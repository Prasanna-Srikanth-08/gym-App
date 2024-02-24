package com.application.gym.dto;

import com.application.gym.Enums.WorkoutType;
import com.application.gym.entity.PersonalDetails;
import com.application.gym.entity.Trainer;
import com.application.gym.entity.User;
import com.application.gym.entity.WorkoutDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String email;
    private String password;

    private String name;

    private PersonalDetails personalDetails;

    private List<WorkoutDetails> workOutSchedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public List<WorkoutDetails> getWorkOutSchedule() {
        return workOutSchedule;
    }

    public void setWorkOutSchedule(List<WorkoutDetails> workOutSchedule) {
        this.workOutSchedule = workOutSchedule;
    }

    public static User prepareUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setPersonalDetails(userDto.getPersonalDetails());
        user.setWorkOutSchedule(userDto.getWorkOutSchedule());
        return user;
    }
}
