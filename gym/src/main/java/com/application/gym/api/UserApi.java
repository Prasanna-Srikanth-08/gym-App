package com.application.gym.api;

import com.application.gym.dto.*;
import com.application.gym.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Tag(name = "User related API's")
public class UserApi {

    @Autowired
    private UserService userService;

    //worked
    @Operation(description = "Endpoint to verify user login credentials",
            summary = "User Login",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @PostMapping("/user/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) throws BadRequestException {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    @Operation(description = "Using this endpoint users can add and update their personal details",
            summary = "Add personal details",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @PutMapping("/user/personaldetails/add")
    public ResponseEntity<PersonalDetailsDto> addPersonalDetails(@RequestBody PersonalDetailsDto personalDetailsDto)
                                                                    throws BadRequestException {
        return new ResponseEntity<>(userService.addPersonalDetails(personalDetailsDto),HttpStatus.CREATED);
    }

    @Operation(description = "Using this endpoint users can register for the event",
            summary = "Register Event",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @PutMapping ("/event/participant/add/{eventId}/{userId}")
    public ResponseEntity<EventDto> registerUserForEvent(@PathVariable int eventId, @PathVariable Long userId) throws BadRequestException {
        return new ResponseEntity<>(userService.registerUserForEvent(eventId,userId),HttpStatus.OK);
    }

    @Operation(description = "To fetch user all details by their user id",
            summary = "Fetch user",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @GetMapping("/user/find/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) throws BadRequestException {
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @Operation(description = "To fetch trainer details by using user id",
            summary = "Fetch Trainer",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @GetMapping("/user/trainerDetails")
    public ResponseEntity<TrainerDto> getTraierDetails(@RequestParam("id") Long id) throws BadRequestException {
        return new ResponseEntity<>(userService.getTrainerDetails(id),HttpStatus.OK);
    }
}
