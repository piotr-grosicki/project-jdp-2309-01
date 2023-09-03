package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTestSuite {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;
    private Group group;
    private Product product;
    
    
    @BeforeEach
    void setUp() {
        
        group = Group.builder()
                     .name("group")
                     .build();
        groupRepository.save(group);
        product = Product.builder()
                         .name("product")
                         .description("description")
                         .price(1000.01)
                         .group(group)
                         .build();
        productRepository.save(product);
    }
    
    @AfterEach
    void clearUp() {
        
        Long groupId = group.getId();
        groupRepository.deleteById(groupId);
    }
    
    @Test
    void shouldCreateData() {
        
        //given
        Long productId = product.getId();
        //when
        Optional<Product> optionalProduct = productRepository.findById(productId);
        //then
        assertTrue(optionalProduct.isPresent());
    }
    
    @Test
    void shouldReadData() {
        
        //when
        List<Product> productList = productRepository.findAll();
        Product product1 = productList.get(0);
        //then
        assertEquals(product, product1);
        assertEquals(1000.01, product1.getPrice());
    }
    
    @Test
    void shouldUpdateData() {
        
        //given
        String newDescription = "new description";
        double newPrice = 666.66;
        //when
        Product product1 = productRepository.findAll().get(0);
        product1.setDescription(newDescription);
        product1.setPrice(newPrice);
        productRepository.save(product1);
        //then
        assertTrue(productRepository.findById(product.getId()).isPresent());
        assertEquals(newDescription, productRepository.findById(product.getId()).get().getDescription());
        assertEquals(newPrice, productRepository.findById(product.getId()).get().getPrice());
        assertEquals(product.getId(), productRepository.findAll().get(0).getId());
    }
    
    @Test
    void shouldDeleteData() {
        
        //when
        productRepository.delete(product);
        //then
        assertFalse(productRepository.existsById(product.getId()));
    }
    
    @Test
    void shouldProperlyAddRelationsBetweenProductsAndGroups() {
        
        //given
        Product product2 = Product.builder()
                                  .name("product2")
                                  .description("description2")
                                  .price(2000.02)
                                  .group(group)
                                  .build();
        productRepository.save(product2);
        //when
        List<Product> foundList = productRepository.findAll();
        //then
        assertEquals(2, foundList.size());
        assertTrue(foundList.contains(product) && foundList.contains(product2));
        assertEquals(foundList.get(0).getGroup(), foundList.get(1).getGroup());
        assertEquals(group, foundList.get(0).getGroup());
    }
}