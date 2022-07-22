package com.epam.spring.homework3.exception;

public class TokenNotValidException extends RuntimeException {
    private static final long serialVersionUID = -1099922431208189741L;

    public TokenNotValidException(String message) {
        super(message);
    }
}
