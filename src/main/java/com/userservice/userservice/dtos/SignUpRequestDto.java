package com.userservice.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String emailId;
    private String password;
    private String fullName;
}
