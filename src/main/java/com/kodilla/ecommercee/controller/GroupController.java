/*
package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return new ArrayList<>();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addGroup(@RequestBody GroupDto groupDto) {

    }

    @GetMapping("/{id}")
    public GroupDto getGroup(@PathVariable Long id) {
        return new GroupDto(
                1L, "Group by Id", new ArrayList<ProductDto>()
        );
    }

    @PutMapping({"/{id}"})
    public GroupDto editGroup(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        return new GroupDto(
                1L, "Group edited", new ArrayList<ProductDto>()
        );
    }
}
*/
