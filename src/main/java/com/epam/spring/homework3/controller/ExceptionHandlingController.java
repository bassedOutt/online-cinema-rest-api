package com.epam.spring.homework3.controller;


import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.exception.ApiError;
import com.epam.spring.homework3.exception.SeatIsAlreadyTakenException;
import com.epam.spring.homework3.exception.TokenNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.spring.homework3.exception.ErrorType.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlingController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiError handleEntityNotFoundException(EntityNotFoundException ex, HandlerMethod hm) {
        log.error("handleEntityNotFoundException : {}. method: {}", ex.getMessage(), hm.getMethod().getName());
        return new ApiError(ex.getMessage(), ENTITY_NOT_FOUND_ERROR_TYPE, LocalDateTime.now());
    }

    @ExceptionHandler(SeatIsAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleEntityCreationException(SeatIsAlreadyTakenException ex, HandlerMethod hm) {
        log.error("handleSeatAlreadyTakenException : {}. method: {}", ex.getMessage(), hm.getMethod().getName());
        return new ApiError(ex.getMessage(), ENTITY_CREATION_FAILED_ERROR_TYPE, LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ApiError> handleNotValidArgumentException(MethodArgumentNotValidException ex, HandlerMethod hm) {
        log.error("handleNotValidArgumentException : {}. method: {}", ex.getMessage(), hm.getMethod().getName());
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new ApiError(err.getDefaultMessage(), VALIDATION_ERROR_TYPE,
                        LocalDateTime.now()))
                .collect(Collectors.toList());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleInternalServerError(Exception ex, HandlerMethod hm) {
        log.error("handleInternalServerError : {}. method: {}", ex.getMessage(), hm.getMethod().getName());
        return new ApiError(ex.getMessage(), FATAL_ERROR_TYPE, LocalDateTime.now());
    }

    @ExceptionHandler(TokenNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleTokenNotValidException(Exception ex, HandlerMethod hm) {
        log.error("handleTokenNotValidException : {}. method: {}", ex.getMessage(), hm.getMethod().getName());
        return new ApiError(ex.getMessage(), TOKEN_NOT_VALID_ERROR_TYPE, LocalDateTime.now());
    }


}
