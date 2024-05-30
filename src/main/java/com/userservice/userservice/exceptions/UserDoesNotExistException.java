package com.userservice.userservice.exceptions;

public class UserDoesNotExistException extends Throwable {
    public UserDoesNotExistException(String message){
        super(message);
    }
}
