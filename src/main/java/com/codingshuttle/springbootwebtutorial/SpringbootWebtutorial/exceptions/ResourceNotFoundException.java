package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
