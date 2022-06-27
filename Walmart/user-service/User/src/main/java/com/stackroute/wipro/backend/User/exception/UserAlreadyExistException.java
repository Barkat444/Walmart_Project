package com.stackroute.wipro.backend.User.exception;


public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
