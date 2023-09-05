package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .email("user1@mail.com")
                .username("user1")
                .password("password")
                .isBlocked(false)
                .build();
        user2 = User.builder()
                .email("user2@mail.com")
                .username("user2")
                .password("password")
                .isBlocked(false)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
    }
    @AfterEach()
    void cleaning() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldCreateUser() {
        // Given
        User testUser = User.builder()
                .email("user1@mail.com")
                .username("user1")
                .password("password")
                .isBlocked(false)
                .build();

        // When
        userRepository.save(testUser);
        User foundUser = userRepository.findById(testUser.getId()).orElse(null);

        // Then
        assertEquals(testUser, foundUser);

        // CleanUp
        userRepository.deleteById(testUser.getId());
    }

    @Test
    public void shouldFindUserById() {
        Long userId = user1.getId();
        //When
        User foundUser = userRepository.findById(userId).orElse(null);

        //Then
        assertEquals(user1, foundUser);
        assertEquals(userId, foundUser.getId());
    }

    @Test
    public void shouldReturnAllUsers() {

        //When
        List<User> users = userRepository.findAll();

        //Then
        assertEquals(user1, users.get(0));
        assertEquals(user2, users.get(1));
    }

    @Test
    public void shouldUpdateUser() {
        // Given
        String updatedPassword = "newPassword";
        // When
        user1.setPassword(updatedPassword);
        userRepository.save(user1);
        User updatedUser = userRepository.findById(user1.getId()).orElse(null);
        //Then
        assertEquals(updatedPassword, updatedUser.getPassword());
    }

    @Test
    public void shouldDeleteUser() {
        // When
        userRepository.deleteById(user1.getId());
        // Then
        assertFalse(userRepository.existsById(user1.getId()));
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void relationBetweenUserAndCart() {
        // Given
        User testUser = User.builder()
                .email("user1@mail.com")
                .username("user1")
                .password("password")
                .isBlocked(false)
                .build();

        Cart cart1 = Cart.builder()
                .user(testUser)
                .created(LocalDate.of(2023, 9, 4))
                .build();
        Cart cart2 = Cart.builder()
                .user(testUser)
                .created(LocalDate.now().minusDays(2))
                .build();

        // When
        userRepository.save(testUser);
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        testUser.getCarts().add(cart1);
        testUser.getCarts().add(cart2);
        userRepository.save(testUser);

        // Then
        assertEquals(2, testUser.getCarts().size());
        assertTrue(testUser.getCarts().contains(cart1));
        assertTrue(testUser.getCarts().contains(cart2));

        // CleanUp
        cartRepository.deleteById(cart1.getId());
        cartRepository.deleteById(cart2.getId());
        userRepository.deleteById(testUser.getId());
    }


    @Test
    @Transactional
    void relationBetweenUserAndOrder() {
        // Given
        User testUser = User.builder()
                .email("user1@mail.com")
                .username("user1")
                .password("password")
                .isBlocked(false)
                .build();

        Order order1 = Order.builder()
                .user(testUser)
                .orderDate(Date.valueOf(LocalDate.of(2023, 9, 1)))
                .status("Sent")
                .build();

        Order order2 = Order.builder()
                .user(testUser)
                .orderDate(Date.valueOf(LocalDate.now()))
                .status("In preparing")
                .build();

        // When
        userRepository.save(testUser);
        orderRepository.save(order1);
        orderRepository.save(order2);
        testUser.getOrders().add(order1);
        testUser.getOrders().add(order2);
        userRepository.save(testUser);

        // Then
        assertEquals(2, testUser.getOrders().size());
        assertTrue(testUser.getOrders().contains(order1));
        assertTrue(testUser.getOrders().contains(order2));

        // Clean up
        orderRepository.deleteById(order1.getId());
        orderRepository.deleteById(order2.getId());
        userRepository.deleteById(testUser.getId());
    }

}
