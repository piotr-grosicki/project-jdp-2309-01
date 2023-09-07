package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.cart.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartMapper {
    
    private ProductMapper productMapper;
    
    public CartDto mapCartToCartDto(Cart cart) {
        
        List<Long> productIdList = cart.getProducts()
                                       .stream()
                                       .map(Product::getId)
                                       .toList();
        return new CartDto(cart.getId(),
                           cart.getUser().getId(),
                           cart.getCreated(),
                           productIdList);
    }
    
    public CartProductsDto mapCartToCartProductsDto(Cart cart) {
        
        List<ProductDto> productDtoList = cart.getProducts()
                                              .stream()
                                              .map(productMapper::mapProductToProductDto)
                                              .toList();
        return new CartProductsDto(cart.getId(), productDtoList);
    }
}
