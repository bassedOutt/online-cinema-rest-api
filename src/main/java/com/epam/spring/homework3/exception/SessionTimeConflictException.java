package com.epam.spring.homework3.exception;

public class SessionTimeConflictException extends RuntimeException{
    private static final long serialVersionUID = -7060428228508427909L;

    public SessionTimeConflictException() {
        super("Chosen time conflicts with other sessions");
    }
}
