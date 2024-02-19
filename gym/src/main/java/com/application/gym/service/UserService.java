package com.application.gym.service;

import com.application.gym.dto.*;
import com.application.gym.entity.PersonalDetails;
import com.application.gym.entity.Trainer;
import com.application.gym.entity.User;
import com.application.gym.repository.TrainerRepository;
import com.application.gym.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    public LoginResponse login(LoginDto loginDto) throws BadRequestException {
        Optional<User> findUserByCredentials = userRepository.findByEmailAndPassword(loginDto.getEmail(),
                loginDto.getPassword());
        LoginResponse loginResponse = new LoginResponse();
            if(findUserByCredentials.isPresent()) {
                loginResponse.setLoggedIn(true);
                loginResponse.setLoginMessage("Logged in");
                return loginResponse;
            }
            throw new BadRequestException("Invalid Credentials");
    }

    public PersonalDetailsDto addPersonalDetails(PersonalDetailsDto personalDetailsDto) throws BadRequestException {
        Optional<User> user = userRepository.findById(personalDetailsDto.getId());
        if(user.isEmpty()) {
            throw new BadRequestException("user not found");
        }
        User userFromDb = user.get();
        PersonalDetails personalDetails = new PersonalDetails();
        personalDetails.setAddress(personalDetailsDto.getAddress());
        personalDetails.setAge(personalDetailsDto.getAge());
        personalDetails.setWeight(personalDetailsDto.getWeight());
        personalDetails.setHeightInMeters(personalDetailsDto.getHeightInMeters());
        personalDetails.setMobileNumber(personalDetailsDto.getMobileNumber());
        userFromDb.setPersonalDetails(personalDetails);
        User persistedUser = userRepository.save(userFromDb);
        PersonalDetails personalDetailsFromDb = persistedUser.getPersonalDetails();
        PersonalDetailsDto result = PersonalDetails.preparePersonalDetailsDto(personalDetailsFromDb);
        result.setId(personalDetailsDto.getId());
        return result;
    }

    public UserDto mapTrainerToUser(Long trainerId,Long userId) throws BadRequestException {
        System.out.println("trainer id is "+trainerId+" and userId is "+userId);
        Optional<Trainer> trainerIfExists = trainerRepository.findById(trainerId);
        if(trainerIfExists.isEmpty()) {
            throw new BadRequestException("Trainer with id "+trainerId+" doesn't exists");
        }
        Optional<User> userIfExists = userRepository.findById(userId);
        if(userIfExists.isEmpty()) {
            throw new BadRequestException("User with id "+userId+" doesn't exists");
        }
        Trainer trainer = trainerIfExists.get();
        User user = userIfExists.get();
        user.setTrainer(trainer);
        User persistedUser = userRepository.save(user);
        UserDto userDto =  User.prepareUserDto(persistedUser);
        userDto.setPassword("*********");
        return userDto;
    }

}
