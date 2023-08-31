package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private double price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<Cart> carts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PRODUCT_ORDER",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORDER_ID")
    )
    private List<Order> orders;
}
