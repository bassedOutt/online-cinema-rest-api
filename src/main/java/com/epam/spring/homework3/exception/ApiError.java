package com.epam.spring.homework3.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private ErrorType errorType;
    private LocalDateTime timeStamp;

}
