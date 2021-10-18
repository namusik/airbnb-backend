package com.team11.airbnbbackend.exception;

public class CustomErrorException extends RuntimeException {
    public CustomErrorException(String msg) {
        super(msg);
    }
}