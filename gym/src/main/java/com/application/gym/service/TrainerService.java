package com.application.gym.service;

import com.application.gym.dto.LoginDto;
import com.application.gym.dto.LoginResponse;
import com.application.gym.dto.TrainerDto;
import com.application.gym.dto.UserDto;
import com.application.gym.entity.Trainer;
import com.application.gym.entity.User;
import com.application.gym.repository.TrainerRepository;
import com.application.gym.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserRepository userRepository;

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

    public List<UserDto> getAllTrainees(Long trainerId) {
        List<User> userList  = userRepository.findByTrainerTrainerId(trainerId);
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList) {
            UserDto userDto = User.prepareUserDto(user);
            userDto.setPassword("*******");
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
