package com.kodilla.ecommercee.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private int userId;
    private LocalDate orderDate;
    private String status;
    private List<ProductDto> products;
}




