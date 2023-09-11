package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.error.group.GroupErrorHandler;
import com.kodilla.ecommercee.error.product.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> readAllGroups() {
        return (List<Group>) groupRepository.findAll();
    }

    public Group readGroupById(Long groupId) throws GroupNotFoundException {
        return groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

    public Group updateGroup(Long groupId, Group updatedGroup) throws GroupNotFoundException {
        Group existingGroup = readGroupById(groupId);

        existingGroup.setName(updatedGroup.getName());
        existingGroup.setProducts(updatedGroup.getProducts());

        return saveGroup(existingGroup);
    }

    public void deleteGroupById(Long groupId) throws GroupNotFoundException {
        if (!groupRepository.existsById(groupId)) {
            throw new GroupNotFoundException();
        } else {
            groupRepository.deleteById(groupId);
        }
    }


}
