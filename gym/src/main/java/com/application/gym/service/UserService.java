package com.application.gym.service;

import com.application.gym.dto.LoginDto;
import com.application.gym.dto.UserDto;
import com.application.gym.entity.User;
import com.application.gym.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto addUser(UserDto userDto) throws BadRequestException {
        Optional<User> userIfExists = userRepository.findByEmail(userDto.getEmail());
        if(userIfExists.isPresent()) {
            throw new BadRequestException("User which you are trying to login already exists");
        }
        User user = UserDto.prepareUser(userDto);
        User persistedUser = userRepository.save(user);
        return User.prepareUserDto(persistedUser);
    }

    public Boolean login(LoginDto loginDto) throws BadRequestException {
        Optional<User> findUserByCredentials = userRepository.findByEmailAndPassword(loginDto.getEmail(),
                loginDto.getPassword());
            if(findUserByCredentials.isEmpty()) {
                throw new BadRequestException("User and password is incorrect");
            }
            if(findUserByCredentials.isPresent() && findUserByCredentials.get().getEmail().equalsIgnoreCase(loginDto.getEmail())){
                return true;
            }
            return false;
    }

}
