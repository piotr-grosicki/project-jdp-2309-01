package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class OrderService {
    
    private CartService cartService;
    private OrderRepository orderRepository;
    
    public Order createNewOrder(Long cartId) {
        
        Cart cart = cartService.getCartById(cartId);
        Order order = Order.builder()
                           .user(cart.getUser())
                           .orderDate(LocalDate.now())
                           .status("status")
                           .build();
        order.getProducts()
                  .addAll(cart.getProducts());
        order.getProducts()
                  .forEach(product -> product.getOrders().add(order));
        return orderRepository.save(order);
    }
}
