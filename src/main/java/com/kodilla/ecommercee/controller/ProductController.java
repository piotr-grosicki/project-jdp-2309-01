package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.error.product.GroupNotFoundException;
import com.kodilla.ecommercee.error.product.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProductById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}/add-to-group/{groupId}")
    public ResponseEntity<ProductDto> addProductToGroup(@PathVariable Long productId, @PathVariable Long groupId) throws GroupNotFoundException, ProductNotFoundException {
        Product product = productService.addProductToGroup(productId, groupId);
        return ResponseEntity.ok(productMapper.mapToProductDto(product));
    }

    @PostMapping("/{productId}/remove-from-group")
    public ResponseEntity<Void> removeProductFromGroup(@PathVariable Long productId) {
        productService.removeProductFromGroup(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        Product updatedProduct = productMapper.mapToProduct(productDto);
        Product savedProduct = productService.updateProduct(productId, updatedProduct);
        return ResponseEntity.ok(productMapper.mapToProductDto(savedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}