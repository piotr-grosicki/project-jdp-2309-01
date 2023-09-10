package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {
        return Group.builder()
                .id(groupDto.getId())
                .name(groupDto.getName())
                .products(groupDto.getProductIds().stream()
                        .map(productId -> {
                            Product product = new Product();
                            product.setId(productId);
                            return product;
                        })
                        .toList())
                .build();
    }

    public GroupDto mapToGroupDto(final Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .productIds(mapToProductIdList(group.getProducts()))
                .build();
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }

    public List<Long> mapToProductIdList(final List<Product> productList) {
        return productList.stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }
}
