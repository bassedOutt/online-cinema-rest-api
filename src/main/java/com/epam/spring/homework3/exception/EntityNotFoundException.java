package com.epam.spring.homework3.exception;


public class EntityNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -7569478368207318715L;

    public EntityNotFoundException(){
        super("Entity not found");
    }

    public EntityNotFoundException(String message){
        super(message);
    }

}
