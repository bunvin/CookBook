package com.example.cookbook.ErrorHandeling;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

@Getter
public class AppException extends Exception {
    @Autowired
    private final ErrorMessage errorMessage;

    public AppException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

}
