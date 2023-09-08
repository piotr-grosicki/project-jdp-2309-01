package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    String email;
    String username;
    String password;
    boolean isBlocked;
}
