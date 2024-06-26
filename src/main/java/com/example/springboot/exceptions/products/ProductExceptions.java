package com.example.springboot.exceptions.products;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<String> productNotFoundHandler(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");

    }


}
