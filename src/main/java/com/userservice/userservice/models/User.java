package com.userservice.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String emailId;
    private String password;
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
