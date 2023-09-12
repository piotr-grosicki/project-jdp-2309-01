package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.user.UserDto;
import com.kodilla.ecommercee.error.product.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return userMapper.mapToUserDto(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser( @RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
       userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDto = users.stream()
                .map(userMapper::mapToUserDto)
                .toList();
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto updatedUserDto) {
        User existingUser = userService.getUserById(userId);
        if (existingUser != null) {
            User updatedUser = userMapper.mapToUser(updatedUserDto);
            updatedUser.setId(userId);
            User savedUser = userService.saveUser(updatedUser);
            UserDto savedUserDto = userMapper.mapToUserDto(savedUser);
            return ResponseEntity.ok(savedUserDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
