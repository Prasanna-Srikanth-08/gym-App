package com.application.gym.api;

import com.application.gym.dto.*;
import com.application.gym.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
@Tag(name = "Admin related API's")
public class AdminApi {
    @Autowired
    private AdminService adminService;
    //working
    @Operation(description = "Endpoint to verify admin login using credentials",
                summary = "Admin Login",
                responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                        @ApiResponse(responseCode = "400",description = "Bad Request")
                })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginDto loginDto) throws BadRequestException {
        return new ResponseEntity<>(adminService.adminLogin(loginDto), HttpStatus.OK);
    }
    //working
    @Operation(description = "Endpoint to save user details to database. Admin can add these details and create new user",
            summary = "User creation",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @PostMapping("/user/signup")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws BadRequestException {
        return new ResponseEntity<>(adminService.addUser(userDto), HttpStatus.CREATED);
    }
    //working
    @Operation(description = "Using this api, admin can view all the users",
            summary = "list Users",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @GetMapping("/users/get")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(adminService.getAllUsers(),HttpStatus.OK);
    }
    //working
    @Operation(description = "If admin wants to remove user, he can remove them using this endpoint",
            summary = "Delete user",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws BadRequestException {
        return new ResponseEntity<>(adminService.deleteUser(id),HttpStatus.OK);
    }
    //working
    @Operation(description = "Admin can add trainer and this will store details to db",
            summary = "Add Trainer",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @PostMapping("trainer/signup")
    public ResponseEntity<TrainerDto> addTrainer(@RequestBody TrainerDto trainerDto) throws BadRequestException {
        return new ResponseEntity<>(adminService.addTrainer(trainerDto),HttpStatus.CREATED);
    }
    //working
    @Operation(description = "Admin can list all the trainer details",
            summary = "Get Trainers",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @GetMapping("/trainers/get")
    public ResponseEntity<List<TrainerDto>> getAllTrainers() {
        return new ResponseEntity<>(adminService.getAllTrainers(),HttpStatus.OK);
    }
    //working
    @Operation(description = "Endpoint to remove the trainer. Once admin delete the trainer, entry will be deleted from DB",
            summary = "Delete Trainer",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @DeleteMapping("trainer/delete/{id}")
    public ResponseEntity<String> deleteTrainer(@PathVariable Long id) throws BadRequestException {
        return new ResponseEntity<>(adminService.deleteTrainer(id),HttpStatus.OK);
    }
    //working
    @Operation(description = "If there are any event about to happen in gym, Admin can add an event",
            summary = "Add Event",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @PostMapping("/event/add")
    public ResponseEntity<EventDto> addEvent(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(adminService.addEvent(eventDto),HttpStatus.CREATED);
    }
    //working
    @Operation(summary = "List Event",
            description = "This endpoint users can use it to view the upcoming events",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @GetMapping("/event/list")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return new ResponseEntity<>(adminService.getAllEvents(),HttpStatus.OK);
    }

    @Operation(summary = "List Event",
            description = "This endpoint users can use it to view the upcoming events",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @DeleteMapping("/event/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable("eventId") int eventId) throws BadRequestException {
        return new ResponseEntity<>(adminService.deleteEvent(eventId),HttpStatus.OK);
    }

    @PostMapping("/workout/add")
    @Operation(summary = "Add Workout",
            description = "Admin can add the workout to particular category",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    public ResponseEntity<WorkoutDto> addWorkout(@RequestBody WorkoutDto workoutDto) {
        return new ResponseEntity<>(adminService.addWorkout(workoutDto),HttpStatus.CREATED);
    }

    @GetMapping("/workouts/fetch")
    @Operation(summary = "Get All workouts",
            description = "This endpoint is used to get all the workout added by admin",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    public ResponseEntity<List<WorkoutDto>> getAllWorkouts() {
        return new ResponseEntity<>(adminService.getAllWorkouts(),HttpStatus.OK);
    }

    @Operation(summary = "Delete workout",
             description= "Api to remove the workout from database",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK"),
                    @ApiResponse(responseCode = "400",description = "Bad Request")
            })
    @DeleteMapping("/workout/delete/{workoutId}")
    public ResponseEntity<String> deleteWorkout(@PathVariable int workoutId) throws BadRequestException {
        return new ResponseEntity<>(adminService.deleteWorkout(workoutId),HttpStatus.OK);
    }
}
