package com.kodilla.ecommercee.dto.cart;

import com.kodilla.ecommercee.dto.product.ProductDto;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartProductsDto {
    
    private Long cartId;
    private List<ProductDto> products;
}
