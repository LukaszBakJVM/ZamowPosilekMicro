package com.example.school.exception;

public class SchoolNotFoundException extends RuntimeException{
    public SchoolNotFoundException(String message) {
        super(message);
    }
}
