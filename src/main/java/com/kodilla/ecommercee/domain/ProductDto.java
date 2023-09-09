package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;

    private Long group;
    private Set<Long> carts;
    private Set<Long> orders;
}
