package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.product.ProductDto;
import com.kodilla.ecommercee.error.product.GroupNotFoundException;
import com.kodilla.ecommercee.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductMapper {
    
    private final GroupService groupService;

    public Product mapToProduct(final ProductDto productDto) throws GroupNotFoundException {

        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                (productDto.getGroupId() != null) ? groupService.readGroupById(productDto.getGroupId()) : null,
                (productDto.getCartIds() != null) ? new ArrayList<>() : null,
                (productDto.getOrderIds() != null) ? new ArrayList<>() : null
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        List<Long> cartIds = product.getCarts()
                .stream()
                .map(Cart::getId)
                .toList();

        List<Long> orderIds = product.getOrders()
                .stream()
                .map(Order::getId)
                .toList();

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
                .toList();
    }
}
