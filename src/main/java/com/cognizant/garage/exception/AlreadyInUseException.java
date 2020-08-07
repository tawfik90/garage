package com.cognizant.garage.exception;

public class AlreadyInUseException extends RuntimeException{
    public AlreadyInUseException(String message) {
        super(message);
    }
}
