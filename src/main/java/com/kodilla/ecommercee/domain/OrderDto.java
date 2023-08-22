package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private User user;
    private Date orderDate;
    private String status;

    private List<Product> orderProducts;
}
