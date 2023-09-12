package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GroupRepositoryTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    private Group group;


    @BeforeEach
    void setUp() {
        group = Group.builder()
                .name("Cars")
                .products(new ArrayList<>())
                .build();
        groupRepository.save(group);

        product1 = Product.builder()
                .name("Product1")
                .price(10)
                .group(group)
                .build();

        product2 = Product.builder()
                .name("Product2")
                .price(20)
                .group(group)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);

        group.getProducts().addAll(Arrays.asList(product1, product2));
        groupRepository.save(group);
    }

    @Test
    void testCreateGroup() {
        //then
        assertNotNull(group);

        //clean
        groupRepository.deleteById(group.getId());
    }

    @Test
    void testUpdateGroup() {
        //when
        Optional<Group> groupBeforeUpdate = groupRepository.findById(group.getId());

        group.setName("Toys");
        groupRepository.save(group);

        Optional<Group> groupAfterUpdate = groupRepository.findById(group.getId());

        //then
        assertEquals("Cars", groupBeforeUpdate.get().getName());
        assertEquals("Toys", groupAfterUpdate.get().getName());

        //clean
        groupRepository.deleteById(group.getId());
    }

    @Test
    void testReadGroupById() {
        //when
        Optional<Group> groupById = groupRepository.findById(group.getId());

        //then
        assertTrue(groupById.isPresent());

        //clean
        groupRepository.deleteById(group.getId());
    }

    @Test
    void testReadAllGroups() {
        //when
        List<Group> groups = groupRepository.findAll();

        //then
        assertTrue(!groups.isEmpty());

        //clean
        groupRepository.deleteById(group.getId());
    }

    @Test
    void testDelete() {
        //when
        boolean existBeforeDelete = groupRepository.findById(group.getId()).isPresent();

        groupRepository.deleteById(group.getId());
        boolean stillExistAfterDelete = groupRepository.findById(group.getId()).isPresent();

        //then
        assertTrue(existBeforeDelete);
        assertFalse(stillExistAfterDelete);
    }

    @Test
    void testOneToManyRelationBetweenGroupAndProduct() {
        //given
        Group group2;
        Product product3;
        Product product4;
        Product product5;

        group2 = Group.builder()
                .name("Electronics")
                .products(new ArrayList<>())
                .build();
        groupRepository.save(group2);

        product3 = Product.builder()
                .name("Mp3Player")
                .price(10)
                .group(group2)
                .build();

        product4 = Product.builder()
                .name("IPhone")
                .price(100)
                .group(group2)
                .build();

        product5 = Product.builder()
                .name("Laptop")
                .price(200)
                .group(group2)
                .build();

        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);

        //when
        group2.getProducts().add(product3);
        group2.getProducts().add(product4);
        group2.getProducts().add(product5);
        groupRepository.save(group2);

        //then
        assertNotNull(group2);
        assertEquals(3, group2.getProducts().size());
        assertTrue(group2.getProducts().contains(product3));
        assertTrue(group2.getProducts().contains(product4));
        assertTrue(group2.getProducts().contains(product5));

        //clean
        groupRepository.deleteById(group.getId());
        groupRepository.deleteById(group2.getId());
    }
}
