package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GroupDto {

    private int id;
    private String name;
    private List<Product> listOfProducts = new ArrayList<>();

    public GroupDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
