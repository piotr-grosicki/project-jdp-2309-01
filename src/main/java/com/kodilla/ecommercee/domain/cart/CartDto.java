package com.kodilla.ecommercee.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {
    
    private Long id;
    private Long userId;
    private LocalDate dateCreated;
    private List<Long> products;
}
