package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbServiceGroup {

    private final GroupRepository groupRepository;

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> readAllGroups() {
        return (List<Group>) groupRepository.findAll();
    }

    public Group readGroupById(Long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        Group group = optionalGroup.get();
        return group;
    }

    public void deleteGroupById(Long groupId) {
        groupRepository.deleteById(groupId);
    }


}
