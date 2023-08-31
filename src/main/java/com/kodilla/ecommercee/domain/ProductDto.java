package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;

    private Group group;
    private List<Cart> carts;
    private List<Order> orders;
}
