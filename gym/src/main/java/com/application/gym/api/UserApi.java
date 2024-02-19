package com.application.gym.api;

import com.application.gym.dto.LoginDto;
import com.application.gym.dto.LoginResponse;
import com.application.gym.dto.PersonalDetailsDto;
import com.application.gym.dto.UserDto;
import com.application.gym.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    //worked
    @PostMapping("/user/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) throws BadRequestException {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("/user/personaldetails/add")
    public ResponseEntity<PersonalDetailsDto> addPersonalDetails(@RequestBody PersonalDetailsDto personalDetailsDto)
                                                                    throws BadRequestException {
        return new ResponseEntity<>(userService.addPersonalDetails(personalDetailsDto),HttpStatus.CREATED);
    }

    @PostMapping("/user/mapTrainer/{trainerId}/{userId}")
    public ResponseEntity<UserDto> mapTrainerToUser(@PathVariable("trainerId") Long trainerId, @PathVariable("userId")
                                    Long userId) throws BadRequestException {
        return new ResponseEntity<>(userService.mapTrainerToUser(trainerId,userId),HttpStatus.OK);
    }
}
