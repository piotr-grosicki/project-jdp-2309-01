package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GroupDto {

    private Long id;
    private String name;
    private List<ProductDto> listOfProducts = new ArrayList<>();

}
