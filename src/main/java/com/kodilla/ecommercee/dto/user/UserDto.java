package com.kodilla.ecommercee.dto.user;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
