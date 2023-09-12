package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.error.cart.CartNotFoundException;
import com.kodilla.ecommercee.error.cart.NoProductInCartException;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CartService {
    
    private CartRepository cartRepository;
    private ProductService productService;
    
    public Cart saveCart(Cart cart) {
        
        return cartRepository.save(cart);
    }
    
    public Cart getCartById(Long id) {
        
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }
    
    public Cart createNewCart(User user) {
        
        Cart cart = Cart.builder()
                        .user(user)
                        .created(LocalDate.now())
                        .build();
        return this.saveCart(cart);
    }
    
    public Cart addProductToCart(Long cartId, Long productId) {
        
        Cart cart = this.getCartById(cartId);
        Product product = productService.getProductById(productId);
        cart.getProducts().add(product);
        return this.saveCart(cart);
    }
    
    public Cart deleteProductFromCart(Long productId, Long cartId) {
        
        Cart cart = this.getCartById(cartId);
        Product product = productService.getProductById(productId);
        if (cart.getProducts().remove(product)) {
            return this.saveCart(cart);
        } else {
            throw new NoProductInCartException(productId, cartId);
        }
    }
}
