package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return new ArrayList<>();
    }

    @PostMapping
    public GroupDto addGroup(GroupDto groupDto) {
        return new GroupDto(
            1,"Group added"
        );
    }

    @GetMapping
    public GroupDto getGroup(int id) {
        return new GroupDto(
                id,"Group by Id"
        );
    }

    @PutMapping
    public GroupDto editGroup(GroupDto groupDto) {
        return new GroupDto(
                1,"Group edited"
        );
    }
}
