package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class Cart {
    
    private Long id;
    private User user;
    private LocalDate created;
    private List<Product> products;
}
