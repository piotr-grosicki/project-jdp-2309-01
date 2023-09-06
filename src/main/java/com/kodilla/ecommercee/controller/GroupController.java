package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.DbServiceGroup;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
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
        logger.info("successfully saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable Long groupId) throws GroupNotFoundException {

        dbServiceGroup.deleteGroupById(groupId);
        logger.info("successfully deleted");

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) throws GroupNotFoundException {

        return ResponseEntity.ok(groupMapper.mapToGroupDto(dbServiceGroup.readGroupById(groupId)));
    }

    @PutMapping("/{groupId}")
    ResponseEntity<Void> updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) throws GroupNotFoundException {

        Group updatedGroup = dbServiceGroup.readGroupById(groupId);
        groupDto.setId(updatedGroup.getId());
        dbServiceGroup.saveGroup(groupMapper.maptoGroup((groupDto)));
        logger.info("successfully updated");

        return ResponseEntity.ok().build();
    }
}
