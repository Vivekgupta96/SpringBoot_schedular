package com.masai.SpringBootApp.exception;

public class UserException extends RuntimeException {

    public UserException(String message){
        super(message);
    }
}
