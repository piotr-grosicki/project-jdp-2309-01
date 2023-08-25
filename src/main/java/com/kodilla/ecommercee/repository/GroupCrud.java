package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupCrud extends CrudRepository<Group, Long> {
    List<Group> findAll();
    Group save(Group group);
    Group findById(long id);
}