package com.kodilla.ecommercee.error;

import com.kodilla.ecommercee.error.product.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        return new ResponseEntity<>("Product with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException cartNotFoundException) {
        return new ResponseEntity<>("Cart with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException) {
        return new ResponseEntity<>("Order with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleGroupNotFoundException(GroupNotFoundException groupNotFoundException) {
        return new ResponseEntity<>("Group with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
