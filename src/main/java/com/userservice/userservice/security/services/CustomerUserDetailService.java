package com.userservice.userservice.security.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.userservice.userservice.models.User;
import com.userservice.userservice.repositories.UserRepository;
import com.userservice.userservice.security.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerUserDetailService implements UserDetailsService {

    UserRepository userRepository;
    @Autowired
    public CustomerUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmailId(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(username + " doesn't exist.");
        }
        return new CustomUserDetails(optionalUser.get());
    }
}
