package com.application.gym.api;

import com.application.gym.dto.LoginDto;
import com.application.gym.dto.UserDto;
import com.application.gym.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gym")
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public UserDto addUser(@RequestBody UserDto userDto) throws BadRequestException {
        return userService.addUser(userDto);
    }

    @GetMapping("/login")
    public Boolean login(@RequestBody LoginDto loginDto) throws BadRequestException {
        return userService.login(loginDto);
    }
}
