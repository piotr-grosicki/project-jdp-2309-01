
package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.error.group.GroupErrorHandler;
import com.kodilla.ecommercee.error.product.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import com.kodilla.ecommercee.service.ProductService;
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
    private final GroupService groupService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {

        List<Group> allGroups = groupService.readAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(allGroups));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addGroup(@RequestBody GroupDto groupDto) {

        Group group = groupMapper.mapToGroup(groupDto);
        groupService.saveGroup(group);
        log.info("successfully saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable Long groupId) throws GroupNotFoundException {

        groupService.deleteGroupById(groupId);
        log.info("successfully deleted");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) throws GroupNotFoundException {

        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupService.readGroupById(groupId)));
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) throws GroupNotFoundException {

        Group updatedGroup = groupMapper.mapToGroup(groupDto);
        Group savedGroup = groupService.updateGroup(groupId, updatedGroup);

        return ResponseEntity.ok(groupMapper.mapToGroupDto(savedGroup));
    }
}

