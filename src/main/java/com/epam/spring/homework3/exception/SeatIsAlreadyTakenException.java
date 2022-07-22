package com.epam.spring.homework3.exception;

public class SeatIsAlreadyTakenException extends RuntimeException{

    public SeatIsAlreadyTakenException() {
        super("This seat is already taken by another visitor");
    }

    public SeatIsAlreadyTakenException(String message) {
        super(message);
    }
}
