package com.kodilla.ecommercee.domain;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id;
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> listOfProducts = new ArrayList<>();

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
