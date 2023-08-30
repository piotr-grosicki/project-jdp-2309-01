package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
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
    private Cart cart;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .email("user@gmail.com")
                .username("user")
                .password("password")
                .generatedKey("key")
                .expirationDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .isBlocked(false)
                .build();

        userRepository.save(user);

        cart = Cart.builder()
                .user(user)
                .created(LocalDate.now().minusDays(1))
                .build();

        cartRepository.save(cart);
    }

    @Test
    public void cartRepositoryCreateTestSuite() {
        // When
        List<Cart> carts = (List<Cart>) cartRepository.findAll();

        // Then
        assertFalse(carts.isEmpty());
    }

    @Test
    public void cartRepositoryReadTestSuite() {
        // When
        Optional<Cart> retrievedCart = cartRepository.findById(cart.getId());

        // Then
        assertTrue(retrievedCart.isPresent());
        assertEquals(user, retrievedCart.get().getUser());
        assertEquals(cart.getCreated(), retrievedCart.get().getCreated());
    }



}