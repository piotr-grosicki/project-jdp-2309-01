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
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;
    private Group group;
    private Group group2;

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

        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
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
        assertNotEquals(groupBeforeUpdate, groupAfterUpdate);

        //clean
        groupRepository.deleteById(group.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
    }

    @Test
    void testReadGroupById() {
        //when
        Optional groupById = groupRepository.findById(group.getId());

        //then
        assertTrue(groupById.isPresent());

        //clean
        groupRepository.deleteById(group.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
    }

    @Test
    void testReadAllGroups() {
        //when
        List<Group> groups = (List<Group>) groupRepository.findAll();

        //then
        assertTrue(groups.size() > 0);

        //clean
        groupRepository.deleteById(group.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
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
        productRepository.deleteById(product3.getId());
        productRepository.deleteById(product4.getId());
        productRepository.deleteById(product5.getId());
        groupRepository.deleteById(group2.getId());

    }
}
