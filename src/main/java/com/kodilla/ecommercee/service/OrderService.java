package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.error.order.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private CartService cartService;
    private OrderRepository orderRepository;
    private ProductService productService;

    public Order createOrder(Long cartId) {
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

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Order updateOrder(Long id, Order order) {
        Order updatedOrder = this.getOrderById(id);
        updatedOrder.setUser(order.getUser());
        updatedOrder.setOrderDate(order.getOrderDate());
        updatedOrder.setStatus(order.getStatus());
        updatedOrder.setProducts(order.getProducts());
        return this.saveOrder(updatedOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order addProductToOrder(Long productId, Long orderId) {
        Product product = productService.getProductById(productId);
        Order order = this.getOrderById(orderId);
        order.getProducts().add(product);
        return this.saveOrder(order);
    }

    public void deleteProductFromOrder(Long productId, Long orderId) {
        Order order = this.getOrderById(orderId);
        Product product = productService.getProductById(productId);
        order.getProducts().remove(product);
        this.saveOrder(order);
    }

    public List<Product> getAllProducts(Long orderId) {
        return this.getOrderById(orderId).getProducts();

    }
}
