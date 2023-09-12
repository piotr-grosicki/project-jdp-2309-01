package com.kodilla.ecommercee.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;
    private Long groupId;
    private List<Long> cartIds;
    private List<Long> orderIds;
}
