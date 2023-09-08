package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.error.product.GroupNotFoundException;
import com.kodilla.ecommercee.service.GroupService;
import com.kodilla.ecommercee.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductMapper {

    private final ProductService productService;
    private final GroupService groupService;

    @SneakyThrows
    public Product mapToProduct(final ProductDto productDto) {

        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                (productDto.getGroupId() != null) ? groupService.getGroupById(productDto.getGroupId()) : null,
                (productDto.getCartIds() != null) ? new ArrayList<>() : null,
                (productDto.getOrderIds() != null) ? new ArrayList<>() : null
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        List<Long> cartIds = product.getCarts()
                .stream()
                .map(Cart::getId)
                .collect(Collectors.toList());

        List<Long> orderIds = product.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        Long groupId = (product.getGroup() != null) ? product.getGroup().getId() : null;

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                groupId,
                cartIds,
                orderIds
        );
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }
}


