package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {


    // ----------------------------------------------------------------------------------
    //  delete if ProductMapper implemented

    public Product mapToProduct(final ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .build();
    }

    public ProductDto mapToProductDto(final Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .build();
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> listOfProducts) {
        return listOfProducts.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(final List<ProductDto> listOfProductsDto) {
        return listOfProductsDto.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    // --------------------------------------------------------------------------------

    public Group maptoGroup(final GroupDto groupDto) {
        return Group.builder()
                .id(groupDto.getId())
                .name(groupDto.getName())
                .products(groupDto.getListOfProducts().stream()
                        .map(this::mapToProduct)
                        .toList())
                .build();
    }

    public GroupDto mapToGroupDto(final Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .listOfProducts(mapToProductDtoList(group.getProducts()))
                .build();
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
