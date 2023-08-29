package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartRepositoryTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void cartRepositoryCreateTestSuite() {
        // Given
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setUsername("user");
        user.setPassword("password");
        user.setGeneratedKey("key");
        user.setExpirationDate(Date.valueOf(LocalDate.now().plusDays(1)));
        user.setBlocked(false);

        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCreated(LocalDate.now().minusDays(1));

        // When
        cartRepository.save(cart);
        List<Cart> carts = (List<Cart>) cartRepository.findAll();

        // Then
        assertFalse(carts.isEmpty());
    }

    @Test
    public void testUserTableCreation() {
        // Given
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setUsername("user");
        user.setPassword("password");
        user.setGeneratedKey("key");
        user.setExpirationDate(Date.valueOf(LocalDate.now().plusDays(1)));
        user.setBlocked(false);

        // When
        userRepository.save(user);
        User savedUser = userRepository.findById(user.getId()).orElse(null);

        // Then
        assertNotNull(savedUser);
        assertEquals("user@gmail.com", savedUser.getEmail());
    }

}