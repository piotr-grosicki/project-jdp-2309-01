package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="group_s")
public class Group {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    private String name;

//    @OneToMany(
//            targetEntity = Product.class,
//            mappedBy = "group",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    @Builder.Default
//    private List<Product> listOfProducts = new ArrayList<>();

}
