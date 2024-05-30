package com.userservice.userservice.controllers;

import com.userservice.userservice.dtos.*;
import com.userservice.userservice.exceptions.UserAlreadyExistException;
import com.userservice.userservice.exceptions.UserDoesNotExistException;
import com.userservice.userservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistException {
        UserDto userDto = authService.signUp(signUpRequestDto.getEmailId(),signUpRequestDto.getPassword(),signUpRequestDto.getFullName());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) throws UserDoesNotExistException {
        return authService.login(loginRequestDto.getEmailId(), loginRequestDto.getPassword());
    }

    @PostMapping("/validate")
    public ValidateTokenResponseDto validate(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) throws UserDoesNotExistException {
        return authService.validate(validateTokenRequestDto.getUserId(), validateTokenRequestDto.getToken());
    }
}
