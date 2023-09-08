package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class GroupDto {

    private Long id;
    private String name;
    private List<Long> productIds;

}
