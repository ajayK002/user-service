package com.userservice.userservice.exceptions;

public class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException(String userAlreadyExist) {
        super(userAlreadyExist);
    }
}
