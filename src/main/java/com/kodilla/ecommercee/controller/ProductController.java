package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(1L, "name_test_1", "description_test_1", 1000.0));
        products.add(new ProductDto(2L, "name_test_2", "description_test_2", 2000.0));
        return products;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        if (id == 1) {
            return new ProductDto(1L, "name_test_1", "description_test_1", 1000.0);
        } else if (id == 2) {
            return new ProductDto(2L, "name_test_2", "description_test_2", 2000.0);
        }
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        //TODO
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return new ProductDto(id, "edited_name_test", "edited_description_test", 3000.0);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        //TODO
    }
}
