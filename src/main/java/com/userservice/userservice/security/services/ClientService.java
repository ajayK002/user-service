package com.userservice.userservice.security.services;

import com.userservice.userservice.security.repositories.JpaRegisteredClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    JpaRegisteredClientRepository jpaRegisteredClientRepository;
    public void registerClient(RegisteredClient registeredClient){
        jpaRegisteredClientRepository.save(registeredClient);
    }
}
