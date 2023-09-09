package com.kodilla.ecommercee.error.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class UserNotFoundDto {
    
    private String message;
    private HttpStatus httpStatus;
}
