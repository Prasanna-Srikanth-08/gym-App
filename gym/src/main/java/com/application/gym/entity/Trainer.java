package com.application.gym.entity;

import com.application.gym.dto.TrainerDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long trainerId;
    private String email;
    private String password;
    private String name;
    private String trainerType; // weight training or Weight Loss or Zumba

    private Long mobileNo;

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainerType() {
        return trainerType;
    }

    public void setTrainerType(String trainerType) {
        this.trainerType = trainerType;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }


    public static TrainerDto prepareTrainerDto(Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setTrainerId(trainer.getTrainerId());
        trainerDto.setName(trainer.getName());
        trainerDto.setEmail(trainer.getEmail());
        trainerDto.setTrainerType(trainer.getTrainerType());
        trainerDto.setPassword(trainer.getPassword());
        trainerDto.setMobileNo(trainer.getMobileNo());
        return trainerDto;
    }
}
