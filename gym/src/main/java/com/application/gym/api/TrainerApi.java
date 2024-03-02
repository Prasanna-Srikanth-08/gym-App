package com.application.gym.api;

import com.application.gym.dto.*;
import com.application.gym.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "Trainer related API's")
public class TrainerApi {

    @Autowired
    private TrainerService trainerService;

    @Operation(summary = "Trainer login",
            description = "Api to verify trainer login credentials",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @GetMapping("/trainer/login")
    public ResponseEntity<LoginResponse> login(@RequestBody TrainerDto trainerDto) throws BadRequestException {
        return new ResponseEntity<>(trainerService.login(trainerDto), HttpStatus.OK);
    }

    @Operation(summary = "Get All Trainees",
            description = "As a trainer, he can see who are all the trainee's under him.",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @GetMapping("/trainees/get/{trainerId}")
    public ResponseEntity<List<UserDto>> getAllTrainees(@PathVariable Long trainerId) throws BadRequestException {
        return new ResponseEntity<>(trainerService.getAllTrainees(trainerId),HttpStatus.OK);
    }

    @Operation(summary = "Align trainer to user",
            description = "Trainer can choose their trainee based on their profile",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @PutMapping("/user/map/{trainerId}/{userId}")
    public ResponseEntity<TrainerDto> mapUserToTrainer(@PathVariable Long trainerId, @PathVariable Long userId) throws BadRequestException {
        return new ResponseEntity<>(trainerService.mapTrainerToUser(trainerId, userId),HttpStatus.CREATED);
    }

    @Operation(summary = "Assign workout to user",
            description = "Trainer can assign the workout to user for particular dates",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @PutMapping("/user/workout/map")
    public ResponseEntity<UserDto> mapWorkoutToUser(@RequestBody MapWorkoutToUserDto  mapWorkoutToUserDto) throws BadRequestException {
        return new ResponseEntity<>(trainerService.mapWorkoutToUser(mapWorkoutToUserDto),HttpStatus.OK);
    }

    @Operation(summary = "Filter users",
            description = "Trainer can filter the users based on workout type",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @GetMapping("/filter/users/{trainerId}")
    public ResponseEntity<List<UserDto>> getUsersByWorkoutType(@PathVariable Long trainerId) throws BadRequestException {
        return new ResponseEntity<>(trainerService.filterUsersByWorkoutType(trainerId),HttpStatus.OK);
    }
}
