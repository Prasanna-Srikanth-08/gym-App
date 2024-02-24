package com.application.gym.service;

import com.application.gym.Enums.WorkoutType;
import com.application.gym.dto.*;
import com.application.gym.entity.Trainer;
import com.application.gym.entity.User;
import com.application.gym.entity.Workout;
import com.application.gym.entity.WorkoutDetails;
import com.application.gym.repository.TrainerRepository;
import com.application.gym.repository.UserRepository;
import com.application.gym.repository.WorkoutRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public LoginResponse login(TrainerDto trainerDto) throws BadRequestException {
        Optional<Trainer> findUserByCredentials = trainerRepository.findByEmailAndPassword(trainerDto.getEmail(),
                trainerDto.getPassword());
        LoginResponse loginResponse = new LoginResponse();
        if(findUserByCredentials.isPresent()) {
            loginResponse.setLoggedIn(true);
            loginResponse.setLoginMessage("Logged in");
            return loginResponse;
        }
        throw new BadRequestException("Invalid Credentials");
    }

    public List<UserDto> getAllTrainees(Long trainerId) throws BadRequestException {
        Optional<Trainer> trainer  = trainerRepository.findById(trainerId);
        if(trainer.isEmpty()) {
            throw new BadRequestException("No trainer found for given id");
        }
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : trainer.get().getTrainee()) {
            UserDto userDto = User.prepareUserDto(user);
            userDto.setPassword("*******");
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public TrainerDto mapTrainerToUser(Long trainerId, Long userId) throws BadRequestException {
        System.out.println("trainer id is "+trainerId+" and userId is "+userId);
        Optional<Trainer> trainerIfExists = trainerRepository.findById(trainerId);
        if(trainerIfExists.isEmpty()) {
            throw new BadRequestException("Trainer with id "+trainerId+" doesn't exists");
        }
        Optional<User> userIfExists = userRepository.findById(userId);
        if(userIfExists.isEmpty()) {
            throw new BadRequestException("User with id "+userId+" doesn't exists");
        }
        User user = userIfExists.get();
        Trainer trainer = trainerIfExists.get();
        List<User> traineeList = trainer.getTrainee();
        traineeList.add(user);
        trainer.setTrainee(traineeList);
        Trainer persistedTrainer = trainerRepository.save(trainer);
        TrainerDto trainerDto = Trainer.prepareTrainerDto(persistedTrainer);
        trainerDto.setPassword("**********");

        return trainerDto;
    }

    public UserDto mapWorkoutToUser(MapWorkoutToUserDto mapWorkoutToUserDto) throws BadRequestException {
        int workoutId = mapWorkoutToUserDto.getWorkoutId();;
        Long userId = mapWorkoutToUserDto.getUserId();
        Date workoutDate = mapWorkoutToUserDto.getWorkoutDate();
        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if(workoutOptional.isEmpty()) {
            System.out.println("workout for given id not found");
            throw new BadRequestException("workout for given id not found");
        }
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            System.out.println("user for given id not found");
            throw new BadRequestException("user for given id not found");
        }
        User user = userOptional.get();
        List<WorkoutDetails> workoutSchedule = new ArrayList<>();
        WorkoutDetails workoutDetails = new WorkoutDetails();
        workoutDetails.setWorkoutDate(workoutDate);
        workoutDetails.setWorkoutName(workoutOptional.get().getWorkoutName());
        workoutDetails.setType(workoutOptional.get().getType());
        if(user.getWorkOutSchedule() == null) {
            workoutSchedule.add(workoutDetails);
        }
        else{
            workoutSchedule = user.getWorkOutSchedule();
            workoutSchedule.add(workoutDetails);
        }
        user.setWorkOutSchedule(workoutSchedule);
        User persistedUser = userRepository.save(user);
        UserDto result = User.prepareUserDto(persistedUser);
        result.setPassword("*********");
        return result;
    }

    public List<UserDto> filterUsersByWorkoutType(Long trainerId) throws BadRequestException {
        Optional<Trainer> trainer = trainerRepository.findById(trainerId);
        if(trainer.isEmpty()) {
            throw new BadRequestException("Trainer not found for given id");
        }
        String workoutType = trainer.get().getTrainerType();
        System.out.println(workoutType);
        List<User> userList = userRepository.findByPersonalDetailsWorkoutType(WorkoutType.valueOf(workoutType));
        System.out.println("after user repo fetch");
        List<UserDto> res = new ArrayList<>();
        for(User user : userList) {
            UserDto userDto = User.prepareUserDto(user);
            res.add(userDto);
        }
        return res;
    }
}
