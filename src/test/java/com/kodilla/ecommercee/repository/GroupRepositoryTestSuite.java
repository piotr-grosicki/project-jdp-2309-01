package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class GroupRepositoryTestSuite {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    Product product1;
    Product product2;
    Group group;

    @BeforeEach
    void setUp() {
        product1 = Product.builder()
                .name("Product1")
                .price(10)
                .build();

        product2 = Product.builder()
                .name("Product2")
                .price(20)
                .build();

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        group = Group.builder()
                .name("Cars")
                .products(products)
                .build();
    }

    @Test
    void testCreateGroup() {
        //when
        Group savedGroup = groupRepository.save(group);

        //then
        assertNotNull(savedGroup);

        //clean
        groupRepository.deleteById(group.getId());
    }

    @Test
    void testUpdateGroup() {
        //when
        groupRepository.save(group);
        String nameBeforeUpdate= group.getName();

        group.setName("Toys");
        groupRepository.save(group);

        //then
        assertEquals("Cars",nameBeforeUpdate);
        assertEquals("Toys", group.getName());

        //clean
        groupRepository.deleteById(group.getId());
    }

    @Test
    void testReadGroupById() {
        //when
        groupRepository.save(group);

        Optional groupById = groupRepository.findById(group.getId());

        //then
        assertTrue(groupById.isPresent());

        //clean
        groupRepository.deleteById(group.getId());
    }

    @Test
    void testReadAllGroups(){
        //when
        groupRepository.save(group);

        List<Group> groups = (List<Group>)groupRepository.findAll();

        //then
        assertTrue(groups.size()>0);

        //clean
        groupRepository.deleteById(group.getId());
    }

    @Test
    void testDelete() {
        //when
        groupRepository.save(group);

        boolean existBeforeDelete = groupRepository.findById(group.getId()).isPresent();

        groupRepository.deleteById(group.getId());
        boolean stillExistAfterDelete = groupRepository.findById(group.getId()).isPresent();

        //then
        assertTrue(existBeforeDelete);
        assertFalse(stillExistAfterDelete);
    }
}
