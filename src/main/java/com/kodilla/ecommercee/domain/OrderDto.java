package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDto {
    
    private Long id;
    private User user;
    private LocalDate orderDate;
    private String status;
    private List<Product> products;
}
