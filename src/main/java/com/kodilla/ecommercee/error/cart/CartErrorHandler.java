package com.kodilla.ecommercee.error.cart;

import com.kodilla.ecommercee.controller.CartController;
import com.kodilla.ecommercee.error.cart.dto.CartNotFoundDto;
import com.kodilla.ecommercee.error.cart.dto.ProductNotFoundInCartDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(assignableTypes = CartController.class)
@Slf4j
public class CartErrorHandler {

    @ExceptionHandler(CartNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CartNotFoundDto handleCartNotFoundException(CartNotFoundException exception) {
        log.info("There is no CART with ID={}", exception.getId());
        return new CartNotFoundDto("Bad Cart ID requested", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NoProductInCartException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProductNotFoundInCartDto handleNoProductInCartException(NoProductInCartException exception) {
        log.info("There is no PRODUCT with ID={} in CART_ID={}", exception.getProductId(), exception.getCartId());
        return new ProductNotFoundInCartDto("Error. There is no such Product in Cart", HttpStatus.NOT_FOUND);
    }
}
