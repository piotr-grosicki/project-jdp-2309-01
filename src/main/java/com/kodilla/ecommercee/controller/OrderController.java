package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.order.OrderDto;
import com.kodilla.ecommercee.dto.product.ProductDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private OrderService orderService;
    private OrderMapper orderMapper;
    private ProductMapper productMapper;

    @PostMapping("/{cartId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long cartId) {
        log.info("Creating an ORDER from the CART(id={})", cartId);
        Order newOrder = orderService.createOrder(cartId);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderService.saveOrder(newOrder)));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("Fetching all ORDERS");
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        log.info("Fetching ORDER(id={})", orderId);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderService.getOrderById(orderId)));
    }


    @GetMapping("/products/{orderId}")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable Long orderId) {
        log.info("Fetching all PRODUCTS from the ORDER(id={})", orderId);

        List<Product> products = orderService.getAllProducts(orderId);
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto updatedOrderDto) {
        log.info("Updating ORDER(id={})", orderId);
        Order updatedOrder = orderMapper.mapToOrder(updatedOrderDto);
        Order savedOrder = orderService.updateOrder(orderId, updatedOrder);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }
    @PatchMapping("/add-product/{productId}/{orderId}")
    public ResponseEntity<OrderDto> addProductToOrder(@PathVariable Long productId, @PathVariable Long orderId) {
        log.info("Adding PRODUCT(id={}) to ORDER(id={})", productId, orderId);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderService.addProductToOrder(productId, orderId)));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
        log.info("Deleting ORDER(id={})", orderId);
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-product/{productId}/{orderId}")
    public ResponseEntity<Void> deleteProductFromOrder(@PathVariable Long productId, @PathVariable Long orderId) {
        log.info("Deleting PRODUCT(id={}) from ORDER(id={})", productId, orderId);
        orderService.deleteProductFromOrder(productId, orderId);
        return ResponseEntity.noContent().build();
    }
}
