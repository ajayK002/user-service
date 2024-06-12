package com.userservice.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.userservice.userservice.models.Role;
import com.userservice.userservice.models.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

//    private User user;
    private ArrayList<GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public CustomUserDetails(User user){
//        this.user = user;

        for (Role role : user.getRoles()){
            this.authorities.add((new CustomGrantedAuthority(role)));
        }
        this.password = user.getPassword();
        this.username = user.getEmailId();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add((new CustomGrantedAuthority(role)));
//        }
        return this.authorities;
    }

    @Override
    public String getPassword() {
//        return user.getPassword();
        return  this.password;
    }

    @Override
    public String getUsername() {
//        return user.getEmailId();
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return true;
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return true;
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return true;
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
//        return true;
        return this.enabled;
    }
}
