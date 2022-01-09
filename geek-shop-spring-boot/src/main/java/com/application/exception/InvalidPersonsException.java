package com.application.exception;

public class InvalidPersonsException extends RuntimeException{
    public InvalidPersonsException(String message) {
        super(message);
    }
}
