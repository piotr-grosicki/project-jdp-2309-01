package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {
    
    private Long id;
    private int userId;
    private LocalDate dateCreated;
    private List<ProductDto> products;
}
