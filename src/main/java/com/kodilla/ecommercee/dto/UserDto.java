package com.kodilla.ecommercee.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String generatedKey;
    private LocalDate expirationDate;
    private boolean isBlocked;

}
