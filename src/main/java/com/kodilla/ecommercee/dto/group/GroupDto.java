package com.kodilla.ecommercee.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class GroupDto {

    private Long id;
    private String name;
    private List<Long> productIds;

}
