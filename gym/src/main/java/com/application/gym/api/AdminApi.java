package com.application.gym.api;

import com.application.gym.dto.*;
import com.application.gym.service.AdminService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminApi {
    @Autowired
    private AdminService adminService;
    //working
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginDto loginDto) throws BadRequestException {
        return new ResponseEntity<>(adminService.adminLogin(loginDto), HttpStatus.OK);
    }
    //working
    @PostMapping("/user/signup")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws BadRequestException {
        return new ResponseEntity<>(adminService.addUser(userDto), HttpStatus.CREATED);
    }
    //working
    @GetMapping("/users/get")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(adminService.getAllUsers(),HttpStatus.OK);
    }
    //working
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws BadRequestException {
        return new ResponseEntity<>(adminService.deleteUser(id),HttpStatus.OK);
    }
    //working
    @PostMapping("trainer/signup")
    public ResponseEntity<TrainerDto> addTrainer(@RequestBody TrainerDto trainerDto) throws BadRequestException {
        return new ResponseEntity<>(adminService.addTrainer(trainerDto),HttpStatus.CREATED);
    }
    //working
    @GetMapping("/trainers/get")
    public ResponseEntity<List<TrainerDto>> getAllTrainers() {
        return new ResponseEntity<>(adminService.getAllTrainers(),HttpStatus.OK);
    }
    //working
    @DeleteMapping("trainer/delete/{id}")
    public ResponseEntity<String> deleteTrainer(@PathVariable Long id) throws BadRequestException {
        return new ResponseEntity<>(adminService.deleteTrainer(id),HttpStatus.OK);
    }
    //working
    @PostMapping("/event/add")
    public ResponseEntity<EventDto> addEvent(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(adminService.addEvent(eventDto),HttpStatus.CREATED);
    }
    //working
    @GetMapping("event/list")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return new ResponseEntity<>(adminService.getAllEvents(),HttpStatus.OK);
    }
}
