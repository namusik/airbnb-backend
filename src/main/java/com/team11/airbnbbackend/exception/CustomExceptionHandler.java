package com.team11.airbnbbackend.exception;

import com.team11.airbnbbackend.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {CustomErrorException.class})
    public ResponseEntity<Object> handleApiRequestException(RuntimeException ex) {
        ResponseDto restApiException = new ResponseDto("fail",ex.getMessage(),"");

        return new ResponseEntity<>(
                restApiException,
                HttpStatus.OK
        );
    }
}