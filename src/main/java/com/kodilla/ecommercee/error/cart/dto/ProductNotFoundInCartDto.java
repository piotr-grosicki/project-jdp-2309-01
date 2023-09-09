package com.kodilla.ecommercee.error.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ProductNotFoundInCartDto {
    
    private String message;
    private HttpStatus httpStatus;
}
