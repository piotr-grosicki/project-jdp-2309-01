package com.kodilla.ecommercee.error.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ProductNotFoundDto {
    
    private String message;
    private HttpStatus httpStatus;
}
