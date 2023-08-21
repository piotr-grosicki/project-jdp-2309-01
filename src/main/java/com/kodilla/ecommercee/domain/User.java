package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    
    private Long id;
    private String email;
    private String username;
    private String password;
    private Boolean isBlocked;
}
