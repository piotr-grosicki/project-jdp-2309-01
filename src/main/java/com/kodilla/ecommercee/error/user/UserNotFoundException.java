package com.kodilla.ecommercee.error.user;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    
    private final Long id;
    
    public UserNotFoundException(Long id) {
        super("USER_ID not found");
        this.id = id;
    }
}
