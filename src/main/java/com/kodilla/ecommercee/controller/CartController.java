package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {
    
    @PostMapping()
    public ResponseEntity<CartDto> createNewCart(@RequestParam Long userId) {
        
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "test1", "test1", 1.0));
        products.add(new Product(2L, "test2", "test2", 2.0));
        User user = new User(userId, "test", "test", "test", false);
        CartDto body = new CartDto(1L, user, LocalDate.now(), products);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(body);
    }
    
    @GetMapping("/{cartId}")
    public ResponseEntity<List<ProductDto>> fetchAllProducts(@PathVariable Long cartId) {
        
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(1L, "test1", "test1", 1.0));
        products.add(new ProductDto(2L, "test2", "test2", 2.0));
        return ResponseEntity.status(HttpStatus.OK)
                             .body(products);
    }
    
    @PatchMapping("/{cartId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long cartId,
                                                    @RequestParam Long productId) {
        
        List<Product> products = new ArrayList<>();
        products.add(new Product(productId, "test1", "test1", 1.0));
        User user = new User(1L, "test", "test", "test", false);
        CartDto body = new CartDto(cartId, user, LocalDate.now(), products);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .body(body);
    }
    
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId,
                                                        @RequestParam Long productId) {
        
        return ResponseEntity.status(HttpStatus.OK)
                             .body("Deleted productId=" + productId + " from cartId=" + cartId);
    }
    
    @PostMapping("/{cartId}")
    public ResponseEntity<OrderDto> createOrderForCart(@PathVariable Long cartId) {
        
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "test1", "test1", 1.0));
        User user = new User(1L, "test", "test", "test", false);
        Cart cart = new Cart(cartId, user, LocalDate.now(), products);
        OrderDto body = new OrderDto(1L, cart.getUser(), LocalDate.now(), "created", cart.getProducts());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(body);
    }
}
