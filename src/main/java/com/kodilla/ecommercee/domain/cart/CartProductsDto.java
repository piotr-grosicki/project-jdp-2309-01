package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.ProductDto;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartProductsDto {
    
    private Long cartId;
    private List<ProductDto> products;
}
