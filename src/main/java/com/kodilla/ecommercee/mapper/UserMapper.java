package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.user.UserDto;
import org.springframework.stereotype.Component;
@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .generatedKey(user.getGeneratedKey())
                .expirationDate(user.getExpirationDate())
                .isBlocked(user.isBlocked())
                .build();
    }

    public User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .generatedKey(userDto.getGeneratedKey())
                .expirationDate(userDto.getExpirationDate())
                .isBlocked(userDto.isBlocked())
                .build();
    }
}