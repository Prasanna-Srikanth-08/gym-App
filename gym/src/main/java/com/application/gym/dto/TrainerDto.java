package com.application.gym.dto;

import com.application.gym.entity.Trainer;
import com.application.gym.entity.User;

import java.util.List;

public class TrainerDto {

    private Long trainerId;
    private String email;
    private String password;
    private String name;
    private String trainerType; // weight training or Weight Loss or Zumba
    //private List<User> trainee;
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

    public static Trainer prepareTrainer(TrainerDto trainerDto) {
        Trainer trainer = new Trainer();
        trainer.setName(trainerDto.getName());
        trainer.setEmail(trainerDto.getEmail());
        trainer.setTrainerType(trainerDto.getTrainerType());
        trainer.setPassword(trainerDto.getPassword());
        trainer.setMobileNo(trainerDto.getMobileNo());
        trainer.setTrainerId(trainerDto.getTrainerId());
        return trainer;
    }
}
