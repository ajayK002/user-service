package com.userservice.userservice.services;

import com.userservice.userservice.dtos.UserDto;
import com.userservice.userservice.dtos.ValidateTokenResponseDto;
import com.userservice.userservice.exceptions.UserAlreadyExistException;
import com.userservice.userservice.exceptions.UserDoesNotExistException;
import com.userservice.userservice.models.Session;
import com.userservice.userservice.models.User;
import com.userservice.userservice.repositories.SessionRepository;
import com.userservice.userservice.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class AuthService {
    UserRepository userRepository;
    SessionRepository sessionRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Create a SecureRandom instance
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);

        // Generate random characters from the pool and append them to the StringBuilder
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHAR_POOL.length());
            sb.append(CHAR_POOL.charAt(randomIndex));
        }

        return sb.toString();
    }

    public UserDto signUp(String emailId, String password, String fullName) throws UserAlreadyExistException {
        Optional<User> optionalUser = userRepository.findByEmailIdAndPassword(emailId, password);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistException("User already exist");
        }

        User newUser = new User();
        newUser.setEmailId(emailId);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFullName(fullName);

        User savedUser = userRepository.save(newUser);
        return UserDto.from(savedUser);
    }

    public String login(String emailId, String password) throws UserDoesNotExistException {
        Optional<User> optionalUser = userRepository.findByEmailId(emailId);
        if (optionalUser.isEmpty()){
            throw new UserDoesNotExistException("User doesn't exist");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserDoesNotExistException("User doesn't exist");
        }

        String token = generateRandomString(20);

        Session session = new Session();
        session.setUser(optionalUser.get());
        session.setToken(token);

        sessionRepository.save(session);

        return token;
    }

    public ValidateTokenResponseDto validate(Long userId, String token) throws UserDoesNotExistException {
        Optional<Session> optionalSession = sessionRepository.findByUser_IdAndToken(userId, token);
        if(optionalSession.isEmpty()){
            throw new UserDoesNotExistException("Wrong token provided");
        }

        ValidateTokenResponseDto validateTokenResponseDto = new ValidateTokenResponseDto();
        validateTokenResponseDto.setEmailId(userRepository.findById(userId).get().getEmailId());
        validateTokenResponseDto.setUserId(userId);

        return validateTokenResponseDto;
    }
}
