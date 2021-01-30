package com.portfolio.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.portfolio.exceptions.ProfileNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNotFound(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
                new CustomErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        exception.getMessage(),
                        ((ServletWebRequest) request).getRequest().getRequestURI()
                ),
                HttpStatus.NOT_FOUND);
    }

    @AllArgsConstructor
    @Data
    public static class CustomErrorResponse {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime timestamp;

        private int status;

        private String error;

        private String message;

        private String path;
    }
}