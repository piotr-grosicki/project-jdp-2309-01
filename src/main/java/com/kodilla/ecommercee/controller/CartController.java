package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.cart.CartProductsDto;
import com.kodilla.ecommercee.dto.cart.CartDto;
import com.kodilla.ecommercee.dto.order.OrderDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/carts")
public class CartController {
    
    private UserService userService;
    private CartService cartService;
    private OrderService orderService;
    private CartMapper cartMapper;
    private OrderMapper orderMapper;
    
    @PostMapping()
    public ResponseEntity<CartDto> createNewCart(@RequestParam Long userId) {
        
        log.info("Creating new CART for USER_ID={}...", userId);
        User user = userService.getUserById(userId);
        Cart newCart = cartService.createNewCart(user);
        CartDto cartDto = cartMapper.mapCartToCartDto(cartService.saveCart(newCart));
        return ResponseEntity.ok(cartDto);
    }
    
    @GetMapping("/{cartId}")
    public ResponseEntity<CartProductsDto> fetchAllProducts(@PathVariable Long cartId) {
        
        log.info("Fetching all PRODUCTS contained in CART_ID={}...", cartId);
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartMapper.mapCartToCartProductsDto(cart));
    }
    
    @PatchMapping("/{cartId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long cartId,
                                                    @RequestParam Long productId) {
        
        log.info("Adding PRODUCT_ID={} to CART_ID={}...", productId, cartId);
        Cart cart = cartService.addProductToCart(cartId, productId);
        return ResponseEntity.ok(cartMapper.mapCartToCartDto(cart));
    }
    
    @DeleteMapping("/{cartId}")
    public ResponseEntity<CartDto> deleteProductFromCart(@PathVariable Long cartId,
                                                         @RequestParam Long productId) {
        
        log.info("Deleting PRODUCT_ID={} from CART_ID={}...", productId, cartId);
        Cart cart = cartService.deleteProductFromCart(productId, cartId);
        return ResponseEntity.ok(cartMapper.mapCartToCartDto(cart));
    }
    
    @PostMapping("/{cartId}")
    public ResponseEntity<OrderDto> createOrderForCart(@PathVariable Long cartId) {
        
        log.info("Creating new ORDER for CART_ID={}...", cartId);
        Order newOrder = orderService.createOrder(cartId);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(newOrder));
    }
}
