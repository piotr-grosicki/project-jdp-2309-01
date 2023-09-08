package com.kodilla.ecommercee.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.sql.Date;
import java.util.List;
@Getter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private Date orderDate;
    private String status;
    private List<Long> productIds;
}




