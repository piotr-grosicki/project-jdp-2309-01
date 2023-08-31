package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;
    
    @Test
    void shouldReturnProperValues_givenProductEntity_whenFetchDataFromDb() {
        //given
        Group group = Group.builder()
                .name("group")
                .build();
        groupRepository.save(group);
        Product product = Product.builder()
                .name("product")
                .description("description")
                .price(1000.00)
                .group(group)
                .build();
        productRepository.save(product);
        Long groupId = group.getId();
        Long productId = product.getId();
        
        //when
        List<Product> foundList = productRepository.findAll();
        Optional<Product> foundById = productRepository.findById(productId);
        
        //then
        assertEquals(1, foundList.size());
        assertTrue(foundById.isPresent());
        assertEquals(product.getPrice(), foundById.get().getPrice());
        assertEquals(group, foundById.get().getGroup());
        
        //cleanUp
        productRepository.deleteById(productId);
        groupRepository.deleteById(groupId);
    }
}