package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.*;
import static org.junit.Assert.*;

@SpringBootTest
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
        new ArrayList<Product>().add(product);
        product = Product.builder()
                .id(1L)
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
                .expirationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .isBlocked(false)
                .build();
        userRepository.save(user);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        order = Order.builder()
                .id(1L)
                .user(user)
                .status("In progress")
                .products(productList)
                .orderDate(new Date())
                .build();

        orderRepository.save(order);

    }

    @Test

    void shouldFindAllOrders() {
        List<Order> orders = (List<Order>) orderRepository.findAll();

        Assertions.assertNotNull(orders);
        Assertions.assertEquals(1, orders.size());

    }


    @Test
    void shouldFindOrderById() {
        Optional<Order> ordersById = orderRepository.findById(order.getId());
        Assertions.assertNotNull(ordersById);
        Assertions.assertEquals(1L, ordersById.get().getId());

    }

    @Test
     void shouldUpdateOrder() {
        order.setStatus("new update");
        Order orderUpdate = orderRepository.save(order);

        Assertions.assertEquals("new update", orderUpdate.getStatus());

    }

    @Test
   void shouldDeleteOrder(){
      orderRepository.deleteById(order.getId());
       assertFalse(orderRepository.existsById(order.getId()));
        productRepository.deleteById(product.getId());
  }

    @Test
    void relationBetweenOrdersAndProducts() {


      Group  group2 = Group.builder()
                .name("group")
                .build();
        groupRepository.save(group2);


     User  user2 = User.builder()
                .email("user@gmail.com")
                .username("user")
                .password("password")
                .generatedKey("key")
                .expirationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .isBlocked(false)
                .build();
        userRepository.save(user2);

        Product product1 = Product.builder()
                .id(1L)
                .name("product1Name")
                .group(group2)
                .description("product1Description")
                .price(2000)
                .build();
        productRepository.save(product1);

        Product product2 = Product.builder()
                .id(2L)
                .group(group2)
                .name("product2Name")
                .description("product2Description")
                .price(2000)
                .build();
        productRepository.save(product2);

        Order order1 = Order.builder()
                .id(1L)
                .user(user2)
                .status("In progress")
                .products(Arrays.asList(product1,product2))
                .orderDate(new Date())
                .build();
        orderRepository.save(order1);

        Assertions.assertEquals(2, order1.getProducts().size());
        Assertions.assertTrue(order1.getProducts().contains(product1));
        Assertions.assertTrue(order1.getProducts().contains(product2));

    }
}

