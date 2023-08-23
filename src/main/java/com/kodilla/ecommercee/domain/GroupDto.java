package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class GroupDto {

    private Long id;
    private String name;
    //private List<Product> listOfProducts = new ArrayList<>();


//    public GroupDto(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
