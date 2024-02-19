package com.application.gym.api;

import com.application.gym.dto.LoginDto;
import com.application.gym.dto.LoginResponse;
import com.application.gym.dto.TrainerDto;
import com.application.gym.dto.UserDto;
import com.application.gym.service.TrainerService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainerApi {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/trainer/login")
    public ResponseEntity<LoginResponse> login(@RequestBody TrainerDto trainerDto) throws BadRequestException {
        return new ResponseEntity<>(trainerService.login(trainerDto), HttpStatus.OK);
    }

    @GetMapping("/trainees/get/{trainerId}")
    public ResponseEntity<List<UserDto>> getAllTrainees(@PathVariable Long trainerId) {
        return new ResponseEntity<>(trainerService.getAllTrainees(trainerId),HttpStatus.OK);
    }
}
