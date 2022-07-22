package com.epam.spring.homework3.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2122635266707432960L;

    public UserNotFoundException(){
        super("User not found");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}

