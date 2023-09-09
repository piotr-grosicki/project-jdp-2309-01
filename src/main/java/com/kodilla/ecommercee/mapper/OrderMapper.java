package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderMapper {
    
    
    public OrderDto mapOrderToOrderDto(Order order) {
        
        List<Long> productList = order.getProducts()
                            .stream()
                            .map(Product::getId)
                            .toList();
        return new OrderDto(order.getId(),
                            order.getUser().getId(),
                            order.getOrderDate(),
                            order.getStatus(),
                            productList);
    }
}
