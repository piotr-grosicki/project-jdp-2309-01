package com.kodilla.ecommercee.error.product;

import com.kodilla.ecommercee.error.product.dto.ProductNotFoundDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
public class ProductErrorHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProductNotFoundDto handleProductNotFoundException(ProductNotFoundException exception) {
        log.info("There is no CART with ID={}", exception.getId());
        return new ProductNotFoundDto("Bad Product ID requested", HttpStatus.BAD_REQUEST);
    }
}
