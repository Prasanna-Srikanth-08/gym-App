package com.application.gym.entity;

import com.application.gym.dto.UserDto;
import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String password;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personalDetailsId")
    private PersonalDetails personalDetails;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "trainerId")
    private Trainer trainer;

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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public static UserDto prepareUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setPersonalDetails(user.getPersonalDetails());
        userDto.setTrainer(user.getTrainer());
        return userDto;
    }
}
