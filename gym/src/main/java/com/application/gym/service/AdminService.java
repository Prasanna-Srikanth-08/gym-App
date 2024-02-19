package com.application.gym.service;


import com.application.gym.dto.*;
import com.application.gym.entity.Admin;
import com.application.gym.entity.Event;
import com.application.gym.entity.Trainer;
import com.application.gym.entity.User;
import com.application.gym.repository.AdminRepository;
import com.application.gym.repository.EventRepository;
import com.application.gym.repository.TrainerRepository;
import com.application.gym.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private EventRepository eventRepository;

    public LoginResponse adminLogin(LoginDto loginDto) throws BadRequestException {
        Optional<Admin> admin = adminRepository.findByEmail(loginDto.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        if(admin.isPresent() && loginDto.getPassword().equalsIgnoreCase(admin.get().getPassword())) {
            loginResponse.setLoggedIn(true);
            loginResponse.setLoginMessage("Logged In");
            return loginResponse;
        }
        throw new BadRequestException("Invalid Credentials");
    }

    public UserDto addUser(UserDto userDto) throws BadRequestException {
        Optional<User> userIfExists = userRepository.findByEmail(userDto.getEmail());
        if(userIfExists.isPresent()) {
            throw new BadRequestException("User which you are trying to login already exists");
        }
        User user = UserDto.prepareUser(userDto);
        User persistedUser = userRepository.save(user);
        return User.prepareUserDto(persistedUser);
    }

    public List<UserDto> getAllUsers() {
        List<User> userListFromEntity = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userListFromEntity) {
            UserDto userDto = User.prepareUserDto(user);
            userDto.setPassword("********");
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public String deleteUser(Long id) throws BadRequestException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new BadRequestException("User which you requested to delete is not found");
        }
        User userToDelete = user.get();
        userRepository.delete(userToDelete);
        return "User " + user.get().getName() + " is deleted";
    }

    public TrainerDto addTrainer(TrainerDto trainerDto) throws BadRequestException {
        Trainer trainer = TrainerDto.prepareTrainer(trainerDto);
        Optional<Trainer> trainerIfExists = trainerRepository.findByEmail(trainerDto.getEmail());
        if(trainerIfExists.isPresent()) {
            throw new BadRequestException("User already exists");
        }
        Trainer persistedTrainer = trainerRepository.save(trainer);
        return Trainer.prepareTrainerDto(persistedTrainer);
    }

    public List<TrainerDto> getAllTrainers() {
        List<Trainer> trainerListFromEntity = trainerRepository.findAll();
        List<TrainerDto> trainerDtoList = new ArrayList<>();
        for(Trainer trainer : trainerListFromEntity) {
            TrainerDto trainerDto = Trainer.prepareTrainerDto(trainer);
            trainerDto.setPassword("********");
            trainerDtoList.add(trainerDto);
        }
        return trainerDtoList;
    }

    public String deleteTrainer(Long id) throws BadRequestException {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        if(trainer.isEmpty()) {
            throw new BadRequestException("User which you requested to delete is not found");
        }
        Trainer trainerToDelete = trainer.get();
        trainerRepository.delete(trainerToDelete);
        return "Trainer " + trainer.get().getName() + " is deleted";
    }

    public EventDto addEvent(EventDto eventDto) {
        Event event = EventDto.prepareEvent(eventDto);
        Event persistedEvent = eventRepository.save(event);
        return Event.prepareEventDto(persistedEvent);
    }

    public List<EventDto> getAllEvents() {
        List<Event> eventList = eventRepository.findAll();
        List<EventDto> eventDtoList = new ArrayList<>();
        for(Event event : eventList) {
            EventDto eventDto = Event.prepareEventDto(event);
            eventDtoList.add(eventDto);
        }
        return eventDtoList;
    }

}
