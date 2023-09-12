package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartRepositoryTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Cart firstCart;
    private Cart secondCart;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("user@gmail.com")
                .username("user")
                .password("password")
                .generatedKey("key")
                .expirationDate(LocalDate.now().plusDays(1))
                .isBlocked(false)
                .build();

        userRepository.save(user);

        firstCart = Cart.builder()
                .user(user)
                .created(LocalDate.now().minusDays(1))
                .build();

        cartRepository.save(firstCart);
    }

    @Test
    void cartRepositoryCreateTestSuite() {
        // When
        List<Cart> carts = cartRepository.findAll();

        // Then
        assertFalse(carts.isEmpty());

        // CleanUp
        cartRepository.deleteById(firstCart.getId());
        userRepository.deleteById(user.getId());

    }

    @Test
    void cartRepositoryReadTestSuite() {
        // When
        Optional<Cart> retrievedCart = cartRepository.findById(firstCart.getId());

        // Then
        assertTrue(retrievedCart.isPresent());
        assertEquals(user, retrievedCart.get().getUser());
        assertEquals(firstCart.getCreated(), retrievedCart.get().getCreated());

        // CleanUp
        cartRepository.deleteById(firstCart.getId());
        userRepository.deleteById(user.getId());

    }

    @Test
    void cartRepositoryUpdateTestSuite() {
        // Given
        LocalDate newCreatedDate = LocalDate.now().minusDays(2);

        // When
        firstCart.setCreated(newCreatedDate);
        cartRepository.save(firstCart);

        // Then
        Optional<Cart> updatedCart = cartRepository.findById(firstCart.getId());
        assertTrue(updatedCart.isPresent());
        assertEquals(newCreatedDate, updatedCart.get().getCreated());

        // CleanUp
        cartRepository.deleteById(firstCart.getId());
        userRepository.deleteById(user.getId());

    }

    @Test
    void cartRepositoryDeleteTestSuite() {
        // When
        System.out.println(firstCart.getId());
        cartRepository.deleteById(firstCart.getId());

        // Then
        assertEquals(0, user.getCarts().size());
        assertFalse(cartRepository.existsById(firstCart.getId()));

        // CleanUp
        userRepository.deleteById(user.getId());
    }

    @Test
    void ManyToOneRelationBetweenCartAndUserTestSuite() {
        // Given
        secondCart = Cart.builder()
                .user(user)
                .created(LocalDate.now().minusDays(3))
                .build();

        // When
        cartRepository.save(secondCart);
        List<Cart> carts = cartRepository.findAllByUser(user);

        // Then
        assertEquals(2, carts.size());
        assertTrue(carts.contains(firstCart));
        assertTrue(carts.contains(secondCart));

        // CleanUp
        cartRepository.deleteById(firstCart.getId());
        cartRepository.deleteById(secondCart.getId());
        userRepository.deleteById(user.getId());

    }

    @Test
    void manyToManyRelationBetweenCartAndProductTestSuite() {
        // Given
        secondCart = Cart.builder()
                .user(user)
                .created(LocalDate.now().minusDays(3))
                .build();
        cartRepository.save(secondCart);

        Product product1 = Product.builder()
                .name("Product1")
                .description("Information about product1")
                .price(1000)
                .build();
        productRepository.save(product1);

        Product product2 = Product.builder()
                .name("Product2")
                .description("Information about product2")
                .price(2000)
                .build();
        productRepository.save(product2);

        Product product3 = Product.builder()
                .name("Product3")
                .description("Information about product3")
                .price(3000)
                .build();
        productRepository.save(product3);

        // When
        firstCart.getProducts().add(product1);
        firstCart.getProducts().add(product2);
        secondCart.getProducts().add(product2);
        secondCart.getProducts().add(product3);
        cartRepository.save(firstCart);
        cartRepository.save(secondCart);
        Long userId = user.getId();
        Long product1Id = product1.getId();
        Long product2Id = product2.getId();
        Long product3Id = product3.getId();

        // Then
        assertNotNull(firstCart);
        assertNotNull(secondCart);
        assertEquals(2, firstCart.getProducts().size());
        assertEquals(2, secondCart.getProducts().size());
        assertTrue(firstCart.getProducts().contains(product1));
        assertTrue(firstCart.getProducts().contains(product2));
        assertFalse(firstCart.getProducts().contains(product3));
        assertFalse(secondCart.getProducts().contains(product1));
        assertTrue(secondCart.getProducts().contains(product2));
        assertTrue(secondCart.getProducts().contains(product3));

        // CleanUp
        productRepository.deleteById(product1Id);
        productRepository.deleteById(product2Id);
        productRepository.deleteById(product3Id);
        userRepository.deleteById(userId);

    }

}