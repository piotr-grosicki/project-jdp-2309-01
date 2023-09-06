package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class GroupDto {

    private Long id;
    private String name;
    private List<ProductDto> listOfProducts = new ArrayList<>();

}
