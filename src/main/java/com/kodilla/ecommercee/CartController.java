package com.kodilla.ecommercee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {
    
    @PostMapping()
    public ResponseEntity<String> createNewCartWithUserId(@RequestParam Long user_id) {
        
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Created new cart for user_id=" + user_id);
    }
    
    @GetMapping("/{cart_id}")
    public ResponseEntity<List<String>> fetchAllProductsWithCartId(@PathVariable Long cart_id) {
        
        return ResponseEntity.status(HttpStatus.OK)
                             .body(Arrays.asList("Fetched all products in cart_id=" + cart_id));
    }
    
    @PatchMapping("/{cart_id}")
    public ResponseEntity<String> addProductToCartWithCartId(@PathVariable Long cart_id) {
        
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .body("Added product to cart_id=" + cart_id);
    }
    
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<String> deleteProductFromCartWithCartId(@PathVariable Long cart_id) {
        
        return ResponseEntity.status(HttpStatus.OK)
                             .body("Deleted product from cart_id=" + cart_id);
    }
    
    @PostMapping("/{cart_id}")
    public ResponseEntity<String> createOrderForCartWithCartId(@PathVariable Long cart_id) {
        
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Created new order for cart_id" + cart_id);
    }
}
