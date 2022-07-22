package com.epam.spring.homework3.exception;

public class RoleNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -5287304313014565399L;

    public RoleNotFoundException(){
        super("User role not found");
    }

    public RoleNotFoundException(String message){
        super(message);
    }
}
