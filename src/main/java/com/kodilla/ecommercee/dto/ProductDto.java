package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
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
