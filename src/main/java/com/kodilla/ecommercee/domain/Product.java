package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private double price;

     @NotNull
     @ManyToOne
     @JoinColumn()
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
