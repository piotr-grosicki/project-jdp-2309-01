package com.kodilla.ecommercee.error.product;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    
    private final Long id;
    
    public ProductNotFoundException(Long id) {
        super("PRODUCT not found");
        this.id = id;
    }
}
