package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class UserDto {
    private int id;
    String email;
    String username;
    String password;
    boolean isBlocked;
}
