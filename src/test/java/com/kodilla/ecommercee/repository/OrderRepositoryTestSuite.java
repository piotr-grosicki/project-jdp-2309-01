package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrderRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroupRepository groupRepository;

    private User user;
    private Product product;
    private Order order;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .name("productName")
                .description("productDescription")
                .price(1000)
                .build();
        productRepository.save(product);

        user = User.builder()
                .email("user@gmail.com")
                .username("user")
                .password("password")
                .generatedKey("key")
                .expirationDate(LocalDate.now().plusDays(1))
                .isBlocked(false)
                .build();
        userRepository.save(user);

        List<Product> productList = Arrays.asList(product);
        order = Order.builder()
                .user(user)
                .status("In progress")
                .products(productList)
                .orderDate(LocalDate.now().plusDays(1))
                .build();
        orderRepository.save(order);
    }

    @AfterEach
    public void cleanUp() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
        groupRepository.deleteAll();
    }


    @Test
    void shouldFindAllOrders() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        assertNotNull(orders);
        assertEquals(1, orders.size());


    }

    @Test
    void shouldUpdate() {

        assertEquals("In progress", order.getStatus());

        order.setStatus("new update");

        assertEquals("new update", order.getStatus());

    }


    @Test
    void shouldFindOrderById() {
        Optional<Order> ordersById = orderRepository.findById(order.getId());
        assertTrue(ordersById.isPresent());
        assertEquals(2, ordersById.get().getId());
    }


    @Test
    void shouldDeleteOrder() {
        orderRepository.deleteById(order.getId());
        assertFalse(orderRepository.existsById(order.getId()));
    }

    @Test
    void relationBetweenOrdersAndProducts() {
        Group group2 = Group.builder()
                .name("group")
                .build();
        groupRepository.save(group2);

        User user2 = User.builder()
                .email("user2@gmail.com")
                .username("user2")
                .password("password")
                .generatedKey("key2")
                .expirationDate(LocalDate.now().plusDays(1))
                .isBlocked(false)
                .build();
        userRepository.save(user2);

        Product product1 = Product.builder()
                .name("product1Name")
                .group(group2)
                .description("product1Description")
                .price(2000)
                .build();
        productRepository.save(product1);

        Product product2 = Product.builder()
                .name("product2Name")
                .group(group2)
                .description("product2Description")
                .price(2000)
                .build();
        productRepository.save(product2);

        Order order1 = Order.builder()
                .user(user2)
                .status("In progress")
                .products(Arrays.asList(product1, product2))
                .orderDate(LocalDate.now().plusDays(1))
                .build();
        orderRepository.save(order1);


        Order savedOrder = orderRepository.findById(order1.getId()).orElse(null);
        Product savedProduct1 = productRepository.findById(product1.getId()).orElse(null);
        Product savedProduct2 = productRepository.findById(product2.getId()).orElse(null);


        assertNotNull(savedOrder);
        assertEquals(2, savedOrder.getProducts().size());
        assertTrue(savedOrder.getProducts().contains(savedProduct1));
        assertTrue(savedOrder.getProducts().contains(savedProduct2));
    }
}