package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {
    
    private Long id;
    private Long userId;
    private Date dateCreated;
    private List<Long> productIds;
}
