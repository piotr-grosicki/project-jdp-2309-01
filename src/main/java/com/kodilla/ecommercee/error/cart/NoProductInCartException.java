package com.kodilla.ecommercee.error.cart;

import lombok.Getter;

@Getter
public class NoProductInCartException extends RuntimeException {
    
    private final Long productId;
    private final Long cartId;
    
    public NoProductInCartException(Long productId, Long cartId) {
        super("No PRODUCT in CART");
        this.productId = productId;
        this.cartId = cartId;
    }
}
