package com.kodilla.ecommercee.error.order;

public class OrderNotFoundException extends RuntimeException {
    private final Long id;

    public OrderNotFoundException(Long id) {
        super("Order not found");
        this.id = id;
    }
}
