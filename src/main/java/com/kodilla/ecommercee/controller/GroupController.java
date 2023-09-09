/*
package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.DbServiceGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupMapper groupMapper;
    private final DbServiceGroup dbServiceGroup;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {

        List<Group> allGroups = dbServiceGroup.readAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(allGroups));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addGroup(@RequestBody GroupDto groupDto) {

        Group group = groupMapper.maptoGroup(groupDto);
        dbServiceGroup.saveGroup(group);
        log.info("successfully saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable Long groupId) throws GroupNotFoundException {

        dbServiceGroup.deleteGroupById(groupId);
        log.info("successfully deleted");
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) throws GroupNotFoundException {

        return ResponseEntity.ok(groupMapper.mapToGroupDto(dbServiceGroup.readGroupById(groupId)));
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) throws GroupNotFoundException {

        Group groupAfterUpdate = dbServiceGroup.readGroupById(groupId);
        groupAfterUpdate.setName(groupDto.getName());
        groupAfterUpdate.setProducts(groupMapper.mapToProductList(groupDto.getListOfProducts()));
        dbServiceGroup.saveGroup(groupAfterUpdate);
        return ResponseEntity.ok(groupAfterUpdate);
    }
}
*/
