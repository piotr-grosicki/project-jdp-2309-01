package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.cart.Cart;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductMapper {
    
    public ProductDto mapProductToProductDto(Product product) {
        
        Set<Long> cartsIdList = product.getCarts()
                                       .stream()
                                       .map(Cart::getId)
                                       .collect(Collectors.toSet());
        Set<Long> ordersIdList = product.getOrders()
                                        .stream()
                                        .map(Order::getId)
                                        .collect(Collectors.toSet());
        return new ProductDto(product.getId(),
                              product.getName(),
                              product.getDescription(),
                              product.getPrice(),
                              product.getGroup().getId(),
                              cartsIdList,
                              ordersIdList);
    }
}
