package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;
@Getter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private String status;
    private List<Long> productIds;
}




