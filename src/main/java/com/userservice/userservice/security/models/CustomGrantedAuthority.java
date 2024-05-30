package com.userservice.userservice.security.models;

import com.userservice.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;

    public CustomGrantedAuthority(Role role){
        this.authority = role.getName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}