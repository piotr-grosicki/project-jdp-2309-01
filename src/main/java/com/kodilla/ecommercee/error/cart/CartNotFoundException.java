package com.kodilla.ecommercee.error.cart;

import lombok.Getter;

@Getter
public class CartNotFoundException extends RuntimeException {
    
    private final Long id;
    
    public CartNotFoundException(Long id) {
        super("CART not found");
        this.id = id;
    }
}
