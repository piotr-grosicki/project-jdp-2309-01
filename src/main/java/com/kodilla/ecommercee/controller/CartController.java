/*
package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {

        Group testGroup = new Group(1L,"group1", new ArrayList<>());
        List<Cart> testCartList = new ArrayList<>();
        List<Order> testOrderList = new ArrayList<>();

    @PostMapping()
    public ResponseEntity<CartDto> createNewCart(@RequestParam int userId) {

        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(1L, "test1", "test1", 1.0, testGroup, testCartList, testOrderList));
        products.add(new ProductDto(2L, "test2", "test2", 2.0, testGroup, testCartList, testOrderList));
        CartDto body = new CartDto(1L, userId, LocalDate.now(), products);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(body);
    }
    
    @GetMapping("/{cartId}")
    public ResponseEntity<List<ProductDto>> fetchAllProducts(@PathVariable Long cartId) {

        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(1L, "test1", "test1", 1.0, testGroup, testCartList, testOrderList));
        products.add(new ProductDto(2L, "test2", "test2", 2.0, testGroup, testCartList, testOrderList));
        return ResponseEntity.status(HttpStatus.OK)
                             .body(products);
    }
    
    @PatchMapping("/{cartId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long cartId,
                                                    @RequestParam Long productId) {

        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(productId, "test1", "test1", 1.0, testGroup, testCartList, testOrderList));
        CartDto body = new CartDto(cartId, 1, LocalDate.now(), products);
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

        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(1L, "test1", "test1", 1.0, testGroup, testCartList, testOrderList));
        CartDto cart = new CartDto(cartId, 1, LocalDate.now(), products);
        OrderDto body = new OrderDto(1L, 1, LocalDate.now(), "created", cart.getProducts());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(body);
    }
}
*/
