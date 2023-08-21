package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>(Arrays.asList(
                new UserDto(1, "user1@kodilla.com", "user1", "1",false),
                new UserDto(2, "user2@kodilla.com", "user2", "2",false)
        ));
        return users;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return (id == 1)? new UserDto(1, "user1@kodilla.com", "user1", "1",false) : null;
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {

    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

    }
}
