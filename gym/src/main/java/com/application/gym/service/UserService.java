package com.application.gym.service;

import com.application.gym.dto.*;
import com.application.gym.entity.Event;
import com.application.gym.entity.PersonalDetails;
import com.application.gym.entity.Trainer;
import com.application.gym.entity.User;
import com.application.gym.repository.EventRepository;
import com.application.gym.repository.TrainerRepository;
import com.application.gym.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private EventRepository eventRepository;

    public LoginResponse login(LoginDto loginDto) throws BadRequestException {
        Optional<User> findUserByCredentials = userRepository.findByEmailAndPassword(loginDto.getEmail(),
                loginDto.getPassword());
        LoginResponse loginResponse = new LoginResponse();
            if(findUserByCredentials.isPresent()) {
                loginResponse.setLoggedIn(true);
                loginResponse.setLoginMessage("Logged in");
                loginResponse.setId(findUserByCredentials.get().getId());
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
        personalDetails.setWorkoutType(personalDetailsDto.getWorkoutType());
        userFromDb.setPersonalDetails(personalDetails);
        User persistedUser = userRepository.save(userFromDb);
        PersonalDetails personalDetailsFromDb = persistedUser.getPersonalDetails();
        PersonalDetailsDto result = PersonalDetails.preparePersonalDetailsDto(personalDetailsFromDb);
        result.setId(personalDetailsDto.getId());
        return result;
    }

    public EventDto registerUserForEvent(int eventId,Long userId) throws BadRequestException {
        Optional<Event> eventIfExist = eventRepository.findById(eventId);
        if(eventIfExist.isEmpty()) {
            throw new BadRequestException("Event for given event id not found");
        }
        Optional<User> userIfExist = userRepository.findById(userId);
        if(userIfExist.isEmpty()) {
            throw new BadRequestException("user not found for given user id");
        }
        User user = userIfExist.get();
        Event event = eventIfExist.get();
        List<User> usersRegistered = event.getParticipants();
        usersRegistered.add(user);
        event.setParticipants(usersRegistered);
        Event eventPersisted = eventRepository.save(event);
        return Event.prepareEventDto(eventPersisted);
    }

    public UserDto getUserById(Long userId) throws BadRequestException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new BadRequestException("User for given user id is empty");
        }
        UserDto userDto = User.prepareUserDto(user.get());
        userDto.setPassword("*******");
        return userDto;
    }

    public TrainerDto getTrainerDetails(Long id) throws BadRequestException {
        Optional<Trainer> trainer = trainerRepository.getTrainer(id);
        if(trainer.isEmpty()) {
            throw new BadRequestException("No trainer mapped for given user Id");
        }
        TrainerDto trainerDto = Trainer.prepareTrainerDto(trainer.get());
        return trainerDto;
    }
}
